package com.example.playmaker.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResultCode {

    WIN("WIN", "승"),
    DRAW("DRAW", "무승부"),
    DEFEAT("DEFEAT", "패");

    private final String key;
    private final String value;
}
