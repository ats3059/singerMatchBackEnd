package success.singermatch.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> testExceptionHandler(BindingResult bindingResult){
        System.out.println("ExceptionHandler 실행");
        ErrorResponse errorResponse = new ErrorResponse("test",500,"T001",bindingResult);
        log.info("error = {}" ,bindingResult.getAllErrors().toString());
        return new ResponseEntity(errorResponse, HttpStatus.BAD_GATEWAY);
    }

}
