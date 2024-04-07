package com.example.playmaker.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Sex {

    MALE("MALE", "남성"),
    FEMALE("FEMALE", "여성");

    private final String key;
    private final String value;
}
