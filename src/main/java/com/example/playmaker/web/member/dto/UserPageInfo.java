package com.example.playmaker.web.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPageInfo {

    private String nickname;
    private String pfUrl;
    private String birth;
    private String email;
    private String contact;
    private String position;
    private String gameStyle;
    private String selfIntro;
    private String preferredSoccerTeam;
    private String activeArea;
    private String activeTime;
    private String proposalYn;

    @Builder
    public UserPageInfo(String nickname, String pfUrl, String birth, String email, String contact, String position,
                        String gameStyle, String selfIntro, String preferredSoccerTeam, String activeArea,
                        String activeTime, String proposalYn) {

        this.nickname = nickname;
        this.pfUrl = pfUrl;
        this.birth = birth;
        this.email = email;
        this.contact = contact;
        this.position = position;
        this.gameStyle = gameStyle;
        this.selfIntro = selfIntro;
        this.preferredSoccerTeam = preferredSoccerTeam;
        this.activeArea = activeArea;
        this.activeTime = activeTime;
        this.proposalYn = proposalYn;
    }
}
