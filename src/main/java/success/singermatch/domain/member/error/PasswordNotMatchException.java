package success.singermatch.domain.member.error;


public class PasswordNotMatchException extends Exception {

    public PasswordNotMatchException() {}

    public PasswordNotMatchException(String msg) {
        super(msg);
    }
}
