package za.co.momentum.activeshoppe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ActiveShoppeResourceNotFoundException extends RuntimeException {

    public ActiveShoppeResourceNotFoundException() {
        super();
    }
    public ActiveShoppeResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public ActiveShoppeResourceNotFoundException(String message) {
        super(message);
    }
    public ActiveShoppeResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}