package com.example.playmaker.exception;

import com.example.playmaker.code.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{

    private final Error error;
}
