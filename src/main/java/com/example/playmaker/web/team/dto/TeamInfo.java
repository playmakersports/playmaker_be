package com.example.playmaker.web.team.dto;

import com.example.playmaker.code.Item;
import lombok.Builder;
import lombok.Data;

@Data
public class TeamInfo {

    private String teamName;
    private String engName;
    private String teamColor;
    private Item item;
    private String createDt;
    private String activeArea;
    private String logoUrl;
    private String teamIntro;
    private String joinYn;
    private String message;

    @Builder
    private TeamInfo(String teamName, String engName, String teamColor, Item item
            , String createDt, String activeArea, String logoUrl
            , String teamIntro, String joinYn, String message) {
        this.teamName = teamName;
        this.engName = engName;
        this.teamColor = teamColor;
        this.item = item;
        this.createDt = createDt;
        this.activeArea = activeArea;
        this.logoUrl = logoUrl;
        this.teamIntro = teamIntro;
        this.joinYn = joinYn;
        this.message = message;
    }
}
