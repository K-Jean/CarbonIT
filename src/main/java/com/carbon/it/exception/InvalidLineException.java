/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.exception;

public class InvalidLineException extends Exception {

    public InvalidLineException(String message) {
        super(message);
    }

    public InvalidLineException(String message, Throwable cause) {
        super(message, cause);
    }
}
