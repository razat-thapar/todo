package com.razat.todo.exceptions;

public class InvalidAddressException extends RuntimeException{
    public InvalidAddressException(String message){
        super(message);
    }
}
