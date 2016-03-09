package com.denis.myown.low_level_database.exception;

import java.io.IOException;

public class RecordsFileException extends IOException {
    public RecordsFileException() {
    }

    public RecordsFileException(String message) {
        super(message);
    }

    public RecordsFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
