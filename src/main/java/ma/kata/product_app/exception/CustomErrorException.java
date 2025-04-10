package ma.kata.product_app.exception;

import org.springframework.http.HttpStatus;


public class CustomErrorException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus status;

    public CustomErrorException(String errorCode, String message, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
