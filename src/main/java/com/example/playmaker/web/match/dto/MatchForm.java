package com.example.playmaker.web.match.dto;

import com.example.playmaker.code.PrgStatusCode;
import com.example.playmaker.code.ResultCode;
import com.example.playmaker.domain.team.Team;
import lombok.Data;

@Data
public class MatchForm {

    private String matchDt;
    private String matchArea;
    private Team homeTeam;
    private Team awayTeam;
    private Long awayTeamId;
    private String publicMatchYn;
    private int homeTeamScore;
    private int awayTeamScore;
    private PrgStatusCode prgStatusCode;
    private ResultCode resultCode;
    private String homeTeamPlayerRegisterYn;
    private String awayTeamPlayerRegisterYn;
    private String matchEndYn;

}
