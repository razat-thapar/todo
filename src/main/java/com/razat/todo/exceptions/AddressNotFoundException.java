package com.razat.todo.exceptions;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String s) {
        super(s);
    }
}
