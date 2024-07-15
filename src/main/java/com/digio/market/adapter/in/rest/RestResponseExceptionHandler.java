package com.digio.market.adapter.in.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.digio.market.adapter.in.rest.response.ErrorResponse;
import com.digio.market.application.exception.CustomerNotFoundException;
import com.digio.market.application.exception.ProductNotFoundException;
import com.digio.market.application.exception.ReadCustomerException;
import com.digio.market.application.exception.ReadProductsException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ProductNotFoundException.class, CustomerNotFoundException.class})
    protected ResponseEntity<Object> handleServiceValidationError(RuntimeException ex, WebRequest request) {

        ErrorResponse body = ErrorResponse.builder()
            .httpStatus(400)
            .erroMessage(ex.getMessage())
        .build();
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ReadProductsException.class, ReadCustomerException.class})
    protected ResponseEntity<Object> handleInternalServerError(RuntimeException ex, WebRequest request) {

        ErrorResponse body = ErrorResponse.builder()
            .httpStatus(500)
            .erroMessage(ex.getMessage())
        .build();
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    
}
