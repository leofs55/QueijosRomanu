package lest.dev.CommerceMail.exception.User;

public class UserAccessDennied extends RuntimeException {
    public UserAccessDennied(String message) {
        super(message);
    }

    public UserAccessDennied(String message, Throwable cause) {
        super(message, cause);
    }
}
