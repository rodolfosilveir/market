package com.digio.market.adapter.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.digio.market.adapter.in.rest.response.ErrorResponse;
import com.digio.market.application.exception.CustomerNotFoundException;
import com.digio.market.application.exception.ProductNotFoundException;
import com.digio.market.application.exception.ReadCustomerException;
import com.digio.market.application.exception.ReadProductsException;

@ExtendWith(MockitoExtension.class)
class RestResponseExceptionHandlerTest {

    @InjectMocks
    private RestResponseExceptionHandler restResponseExceptionHandler;

    private WebRequest request;

    @BeforeEach
    public void setup() {
        request = mock(WebRequest.class);
    }

    @Test
    void testHandleServiceValidationErrorProductNotFoundException() {
        ProductNotFoundException ex = new ProductNotFoundException("Product not found");

        ResponseEntity<Object> response = restResponseExceptionHandler.handleServiceValidationError(ex, request);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorResponse body = (ErrorResponse) response.getBody();
        assertNotNull(body);
        assertEquals(400, body.getHttpStatus());
        assertEquals("Product not found", body.getErroMessage());
    }

    @Test
    void testHandleServiceValidationErrorCustomerNotFoundException() {
        CustomerNotFoundException ex = new CustomerNotFoundException("Customer not found");

        ResponseEntity<Object> response = restResponseExceptionHandler.handleServiceValidationError(ex, request);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorResponse body = (ErrorResponse) response.getBody();
        assertNotNull(body);
        assertEquals(400, body.getHttpStatus());
        assertEquals("Customer not found", body.getErroMessage());
    }

    @Test
    void testHandleInternalServerErrorReadProductsException() {
        ReadProductsException ex = new ReadProductsException("Error reading products");

        ResponseEntity<Object> response = restResponseExceptionHandler.handleInternalServerError(ex, request);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ErrorResponse body = (ErrorResponse) response.getBody();
        assertNotNull(body);
        assertEquals(500, body.getHttpStatus());
        assertEquals("Error reading products", body.getErroMessage());
    }

    @Test
    void testHandleInternalServerErrorReadCustomerException() {
        ReadCustomerException ex = new ReadCustomerException("Error reading customer");

        ResponseEntity<Object> response = restResponseExceptionHandler.handleInternalServerError(ex, request);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ErrorResponse body = (ErrorResponse) response.getBody();
        assertNotNull(body);
        assertEquals(500, body.getHttpStatus());
        assertEquals("Error reading customer", body.getErroMessage());
    }
    
}
