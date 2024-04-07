package com.example.playmaker.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ROLE_ADMIN("ROLE_ADMIN", "운영자"),
    ROLE_MANAGER("ROLE_MANAGER", "매니저"),
    ROLE_PLAYER("ROLE_PLAYER", "선수");

    private final String key;
    private final String value;
}
