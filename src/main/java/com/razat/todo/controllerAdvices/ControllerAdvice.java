package com.razat.todo.controllerAdvices;

import com.razat.todo.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@RestControllerAdvice
public class ControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);
    @ExceptionHandler(value={InvalidTaskException.class, InvalidUserException.class,InvalidAddressException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleInvalidRequestException(HttpServletRequest httpServletRequest , Exception exception){

        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .message(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .build();
        logger.error("{}",exceptionResponse);
        return exceptionResponse;
    }

    @ExceptionHandler(value={TaskNotFoundException.class, UserNotFoundException.class, FileNotFoundException.class, AddressNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundRequestException(HttpServletRequest httpServletRequest , Exception exception){
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .message(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .httpStatusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .build();
        logger.error("{}",exceptionResponse);
        return exceptionResponse;
    }
    @ExceptionHandler(value={IOException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleException(HttpServletRequest httpServletRequest , Exception exception){
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .message(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(new Date())
                .build();
        Arrays.stream(exception.getStackTrace()).forEach((stackTraceElement -> logger.error("{}",stackTraceElement)));
        logger.error("{}",exceptionResponse);
        return exceptionResponse;
    }


}
