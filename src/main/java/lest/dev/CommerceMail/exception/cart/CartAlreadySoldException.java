package lest.dev.CommerceMail.exception.cart;

public class CartAlreadySoldException extends RuntimeException {

    public CartAlreadySoldException(String message) {
        super(message);
    }

    public CartAlreadySoldException(String message, Throwable cause) {
        super(message, cause);
    }
}
