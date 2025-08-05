package lest.dev.CommerceMail.exception.product;

public class ProductDeletionException extends RuntimeException {
    public ProductDeletionException(String message) {
        super(message);
    }

  public ProductDeletionException(String message, Throwable cause) {
    super(message, cause);
  }
}
