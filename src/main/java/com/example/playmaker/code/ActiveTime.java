package com.example.playmaker.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActiveTime {

    WEEKDAY_0("WEEKDAY_0", "07~11시"),
    WEEKDAY_1("WEEKDAY_1", "12~18시"),
    WEEKDAY_2("WEEKDAY_2", "19~24시"),
    WEEKDAY_3("WEEKDAY_3", "00~06시"),

    SAT_0("SAT_0", "07~11시"),
    SAT_1("SAT_1", "12~18시"),
    SAT_2("SAT_2", "19~24시"),

    SUN_0("SUN_0", "07~11시"),
    SUN_1("SUN_1", "12~18시"),
    SUN_2("SUN_2", "19~24시");


    private final String key;
    private final String value;


    public static ActiveTime of(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        for (ActiveTime a : ActiveTime.values()) {
            if (a.key.equals(key)) {
                return a;
            }
        }

        throw new IllegalArgumentException("일치하는 지역코드가 없습니다.");
    }
}
