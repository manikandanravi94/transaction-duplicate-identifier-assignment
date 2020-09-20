package com.mani.exception;

public class TransactionDuplicateAppException extends Exception {

    public TransactionDuplicateAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionDuplicateAppException() {
    }

    public TransactionDuplicateAppException(String message) {
        super(message);
    }
}
