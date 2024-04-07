package com.example.playmaker.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomExceptionEntity {

    private String errorCode;
    private String errorMessage;

    @Builder
    public CustomExceptionEntity(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
