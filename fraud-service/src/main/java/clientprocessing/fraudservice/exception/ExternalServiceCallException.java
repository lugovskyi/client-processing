package clientprocessing.fraudservice.exception;

public class ExternalServiceCallException extends RuntimeException {
    public ExternalServiceCallException(String message) {
        super(message);
    }
}
