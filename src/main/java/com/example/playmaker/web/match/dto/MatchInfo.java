package com.example.playmaker.web.match.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchInfo {

    String homeTeamName;
    String awayTeamName;
    private String matchDt;
    private String matchArea;

    @Builder
    public MatchInfo(String homeTeamName, String awayTeamName, String matchDt, String matchArea) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.matchDt = matchDt;
        this.matchArea = matchArea;
    }

}
