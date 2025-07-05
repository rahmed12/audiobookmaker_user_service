package com.agent404.audiobook.userservice.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.agent404.audiobook.userservice.userservice.dto.CustomeErrorResponse;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ResponseEntity<CustomeErrorResponse>> handleNotFound(ResourceNotFoundException ex) {
        
        return Mono.just(
            ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new CustomeErrorResponse(ex.getMessage()))
        );
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<CustomeErrorResponse>> handlyAny(Exception ex) {
        return Mono.just(
            ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new CustomeErrorResponse("Interal server error"))
        );
    }

}
