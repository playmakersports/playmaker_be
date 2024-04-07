package com.example.playmaker.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PrgStatusCode {

    BEFORE("BEFORE", "경기전"),
    AFTER("AFTER", "경기종료");

    private final String key;
    private final String value;
}
