package com.example.playmaker.web.member.dto;

import com.example.playmaker.security.Token;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginInfo {

    private String username;
    private String nickname;
    private Token token;

    @Builder
    private LoginInfo(String username, String nickname, Token token) {
        this.username = username;
        this.nickname = nickname;
        this.token = token;
    }
}
