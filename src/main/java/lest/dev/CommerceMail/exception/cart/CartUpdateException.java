package lest.dev.CommerceMail.exception.cart;

public class CartUpdateException extends RuntimeException {
    public CartUpdateException(String message) {
        super(message);
    }

  public CartUpdateException(String message, Throwable cause) {
    super(message, cause);
  }
}
