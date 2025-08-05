package lest.dev.CommerceMail.exception.product;

public class ProductsQuatityExceededException extends RuntimeException {
    public ProductsQuatityExceededException(String message) {
        super(message);
    }

  public ProductsQuatityExceededException(String message, Throwable cause) {
    super(message, cause);
  }
}
