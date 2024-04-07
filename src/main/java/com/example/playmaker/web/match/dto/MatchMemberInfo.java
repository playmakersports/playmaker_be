package com.example.playmaker.web.match.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchMemberInfo {

    private String name;
    private String position;

    @Builder
    public MatchMemberInfo(String name, String position) {
        this.name = name;
        this.position = position;
    }
}
