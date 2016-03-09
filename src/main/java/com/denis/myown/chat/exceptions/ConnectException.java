package com.denis.myown.chat.exceptions;

public class ConnectException extends ApplicationException {
    public ConnectException() {
        super();
    }

    public ConnectException(String message) {
        super(message);
    }

    public ConnectException(String message, Throwable cause) {
        super(message, cause);
    }
}
