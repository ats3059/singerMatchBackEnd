package success.singermatch.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;



@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> testExceptionHandler(BindingResult bindingResult){
        ErrorResponse errRes = makeErrorRes("test",400,"T001",bindingResult);
        return new ResponseEntity(errRes, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> jsonConvertFail(){
        return new ResponseEntity(makeErrorRes(Fail.MESSAGE_CONVERT_FAIL),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(){
        return new ResponseEntity<>(makeErrorRes(Fail.NOT_FOUND),HttpStatus.NOT_FOUND);
    }


    private ErrorResponse makeErrorRes(Fail fail){
        return new ErrorResponse(fail.getReason(),fail.getStatusCode(),fail.getErrCode());
    }
    private ErrorResponse makeErrorRes(String reason , int statusCode, String errCode,BindingResult bindingResult){
        return new ErrorResponse(reason,statusCode,errCode,bindingResult,messageSource);
    }



}

