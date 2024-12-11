package bdb.Backend.exception;

public class InvalidApiResponseException extends RuntimeException{
    public InvalidApiResponseException(String message) {
        super(message);
    }
}
