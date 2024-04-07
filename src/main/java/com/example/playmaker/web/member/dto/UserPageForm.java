package com.example.playmaker.web.member.dto;

import lombok.Data;

@Data
public class UserPageForm {

    private String nickname;
    private String pfUrl;
    private String contact;
    private String position;
    private String gameStyle;
    private String selfIntro;
    private String preferredSoccerTeam;
    private String activeArea;
    private String activeTime;
    private String proposalYn;
}
