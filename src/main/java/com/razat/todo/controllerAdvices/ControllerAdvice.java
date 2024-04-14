package com.razat.todo.controllerAdvices;

import com.razat.todo.exceptions.InvalidTaskException;
import com.razat.todo.exceptions.InvalidUserException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value={InvalidTaskException.class, InvalidUserException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleInvalidRequestException(HttpServletRequest httpServletRequest , Exception exception){
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .message(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return exceptionResponse;
    }
}
