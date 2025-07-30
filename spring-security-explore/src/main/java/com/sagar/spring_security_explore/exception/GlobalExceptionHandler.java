package com.sagar.spring_security_explore.exception;

import com.sagar.spring_security_explore.dto.response.SecurityServiceExplorerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<SecurityServiceExplorerResponse<Object>> handleException(Exception ex) {
        SecurityServiceExplorerResponse<Object> response = new SecurityServiceExplorerResponse<>(
                false,
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

