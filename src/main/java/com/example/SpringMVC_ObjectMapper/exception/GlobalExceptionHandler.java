package com.example.SpringMVC_ObjectMapper.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.SpringMVC_ObjectMapper.exception.ProductNotFoundException;
import com.example.SpringMVC_ObjectMapper.exception.InsufficientDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails("An error has occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(ProductNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InsufficientDataException.class)
    public ResponseEntity<String> handleUserNotFoundException(InsufficientDataException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorDetails> handleJsonProcessingException(JsonProcessingException ex) {
        return new ResponseEntity<>(new ErrorDetails("Error processing JSON: " + ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    private static class ErrorDetails {
        private String message;

        public ErrorDetails(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
