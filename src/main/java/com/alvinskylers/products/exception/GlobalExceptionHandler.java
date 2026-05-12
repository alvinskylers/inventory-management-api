package com.alvinskylers.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException exception,
            WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(
                        error.getField(),
                        error.getDefaultMessage()
                ));
        Map<String, Object> body = new HashMap<>();
        body.put("uri: ", request.getDescription(false).replace("uri=", ""));
        body.put("timestamp: ", LocalDateTime.now());
        body.put("message: ", "Validation failed");
        body.put("error: ", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundExceptions(
            ResourceNotFoundException exception, WebRequest request
    ) {
        return buildErrorResponse(exception.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(
            Exception exception, WebRequest request
    ) {
        return buildErrorResponse(
                "an internal error occurred", request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(
            String message, WebRequest request, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp: ", LocalDateTime.now());
        body.put("message: ", message);
        body.put("uri",request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(body, status);
    }
}
