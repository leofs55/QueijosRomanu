package lest.dev.CommerceMail.exception;

import lest.dev.CommerceMail.exception.cart.CartAlreadySoldException;
import lest.dev.CommerceMail.exception.cart.CartCreationException;
import lest.dev.CommerceMail.exception.cart.CartNotFoundException;
import lest.dev.CommerceMail.exception.cart.CartUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CartExceptionHandler {

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<String> handleCartNotFoundException(CartNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartCreationException.class)
    public ResponseEntity<String> handleCartCreationException(CartCreationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CartUpdateException.class)
    public ResponseEntity<String> handleCartUpdateException(CartUpdateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartAlreadySoldException.class)
    public ResponseEntity<String> handleCartAlreadySoldException(CartAlreadySoldException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}