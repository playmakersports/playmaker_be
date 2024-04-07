package com.example.playmaker.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<CustomExceptionEntity> customExceptionHandler(final CustomException e) {
        return ResponseEntity
                .status(e.getError().getHttpStatus())
                .body(CustomExceptionEntity.builder()
                        .errorCode(e.getError().getErrorCode())
                        .errorMessage(e.getError().getErrorContent())
                        .build());
    }
}
