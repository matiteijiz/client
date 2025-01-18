package com.management.client.exception;

import com.management.client.CustomerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Errores de validación");
        errorResponse.setTimestamp(System.currentTimeMillis());

        StringBuilder details = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
        }
        errorResponse.setDetails(details.toString());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDetails("No hay clientes cargados");
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
    }

}
