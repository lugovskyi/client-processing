package clientprocessing.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidSidException extends RuntimeException {
    public InvalidSidException(String message) {
        super(message);
    }
}
