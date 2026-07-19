package com.user.yesbank.exception;

import com.user.yesbank.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO();

        error.setApiPath(request.getRequestURI());
        error.setErrorCode(HttpStatus.NOT_FOUND);
        error.setErrorMessage(ex.getMessage());
        error.setErrorTime(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(
            CustomerAlreadyExistsException ex,
            HttpServletRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO();

        error.setApiPath(request.getRequestURI());
        error.setErrorCode(HttpStatus.BAD_REQUEST);
        error.setErrorMessage(ex.getMessage());
       error.setErrorTime(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(
            Exception ex,
            HttpServletRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO();

        error.setApiPath(request.getRequestURI());
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setErrorMessage(ex.getMessage());
        error.setErrorTime(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}