package lest.dev.CommerceMail.exception.User;

public class UserDontHaveCartOpenException extends RuntimeException {
    public UserDontHaveCartOpenException(String message) {
        super(message);
    }

    public UserDontHaveCartOpenException(String message, Throwable cause) {
        super(message, cause);
    }
}
