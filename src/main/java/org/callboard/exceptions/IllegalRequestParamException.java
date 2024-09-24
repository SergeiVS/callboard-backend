package org.callboard.exceptions;

public class IllegalRequestParamException extends RuntimeException {
    public IllegalRequestParamException(String message) {
        super(message);
    }
}
