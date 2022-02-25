package success.singermatch.domain.member.error;


public class UserNotFoundException extends Exception {

    public UserNotFoundException() { }

    public UserNotFoundException(String msg) {
        super(msg);
    }


}
