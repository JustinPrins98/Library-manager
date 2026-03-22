package com.justinprins.library.exception;

public class BookNotFoundException extends IllegalArgumentException {
    public BookNotFoundException(String message) {
        super(message);
    }

}
