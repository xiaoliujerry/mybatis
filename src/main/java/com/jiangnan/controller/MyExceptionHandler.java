package com.jiangnan.controller;

import com.jiangnan.common.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public Response<String> handle(ConstraintViolationException e) {
        return Response.fail(50000, e.getMessage());
    }
}
