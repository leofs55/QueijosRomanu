package lest.dev.CommerceMail.exception.User;

public class PermissionDeniedUserAccessExcepetion extends RuntimeException {
    public PermissionDeniedUserAccessExcepetion(String message) {
        super(message);
    }

    public PermissionDeniedUserAccessExcepetion(String message, Throwable cause) {
        super(message, cause);
    }
}
