package com.razat.todo.controllerAdvices;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.util.Date;
@Builder
@Getter
public class ExceptionResponse {
    private String message;
    private String uri;
    private Date timestamp;
    private int httpStatusCode;
}
