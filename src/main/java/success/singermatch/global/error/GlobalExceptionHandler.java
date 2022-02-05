package success.singermatch.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> testExceptionHandler(BindingResult bindingResult){
        System.out.println("ExceptionHandler 실행");

        ErrorResponse errorResponse = new ErrorResponse(
                "test"
                ,400
                ,"T001"
                ,bindingResult
                ,messageSource);

        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }




}
