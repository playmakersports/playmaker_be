package com.example.playmaker.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Item {

    ROLE_ADMIN(1, "축구"),
    ROLE_MANAGER(2, "농구"),
    ROLE_PLAYER(3, "야구");

    private final Integer key;
    private final String value;

}
