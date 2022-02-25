package success.singermatch.domain.member.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import success.singermatch.domain.member.common.MemberStatus;
import success.singermatch.global.error.ErrorResponse;

@Slf4j
@RestControllerAdvice(basePackages = "success.singermatch.domain.member")

public class SignupControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(Exception e) {
        log.info(e.getMessage());
        return new ResponseEntity(makeErrorRes(MemberStatus.USER_NOT_FOUND),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdDuplicationException.class)
    public ResponseEntity<ErrorResponse> idDuplicationException(Exception e) {
        log.info(e.getMessage());
        return new ResponseEntity(makeErrorRes(MemberStatus.ID_ALREADY_EXIST),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<ErrorResponse> passwordNotMatchException(Exception e) {
        log.info(e.getMessage());
        return new ResponseEntity(makeErrorRes(MemberStatus.PASSWORD_NOT_MATCH),HttpStatus.BAD_REQUEST);
    }


    private ErrorResponse makeErrorRes(MemberStatus ms){
        return new ErrorResponse(ms.getReason(),ms.getStatusCode(),ms.getErrCode());
    }

}
