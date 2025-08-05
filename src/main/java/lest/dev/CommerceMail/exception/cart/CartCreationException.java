package lest.dev.CommerceMail.exception.cart;

public class CartCreationException extends RuntimeException {
    public CartCreationException(String message) {
        super(message);
    }

    public CartCreationException(String message, Throwable cause) {
        super(message, cause);

    }
}
