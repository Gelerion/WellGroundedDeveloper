package com.denis.myown.chat.exceptions;

public class LaunchException extends ApplicationException {

    public LaunchException() {
    }

    public LaunchException(String message) {
        super(message);
    }

    public LaunchException(String message, Throwable cause) {
        super(message, cause);
    }
}
