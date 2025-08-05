package lest.dev.CommerceMail.exception.User;

public class UserCreationEmailException extends RuntimeException {
    public UserCreationEmailException(String message) {
        super(message);
    }

    public UserCreationEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
