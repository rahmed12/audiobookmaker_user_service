package com.agent404.audiobook.userservice.userservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.agent404.audiobook.userservice.userservice.dto.CustomeErrorResponse;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ResponseEntity<CustomeErrorResponse>> handleNotFound(ResourceNotFoundException ex) {
        
        return Mono.just(
            ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new CustomeErrorResponse(ex.getMessage()))
        );
    }

    @ExceptionHandler(DuplicateEmailExceptoin.class)
    public Mono<ResponseEntity<CustomeErrorResponse>> handlyDuplicateEmailRecord(DuplicateEmailExceptoin ex) {
        return Mono.just(
            ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(new CustomeErrorResponse(ex.getMessage()))
        );
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<CustomeErrorResponse>> handlyAny(Exception ex) {
        return Mono.just(
            ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new CustomeErrorResponse("Interal server error: " + ex.getMessage()))
        );
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<CustomeErrorResponse>> handleValidation(WebExchangeBindException ex) {
        
        // Log the full validation detail
        logger.warn("Validation failed: {}", ex.getAllErrors());

        // Extract first validation message
        String message = ex.getFieldErrors().stream()
            .map(error -> error.getDefaultMessage())
            .findFirst()
            .orElse("Validation error");

        return Mono.just(ResponseEntity
            .badRequest()
            .body(new CustomeErrorResponse(message)));
    }

}
