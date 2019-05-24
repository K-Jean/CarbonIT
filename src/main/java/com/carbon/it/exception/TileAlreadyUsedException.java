/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.exception;

public class TileAlreadyUsedException extends RuntimeException {

    public TileAlreadyUsedException(String message) {
        super(message);
    }
}
