package com.example.playmaker.web.mercenaryrecruitboard.dto;

import com.example.playmaker.domain.team.Team;
import lombok.Data;

@Data
public class MercenaryRecruitBoardInfo {
    private String gameDt;
    private String gameArea;
    private Integer recruitNum;
    private String mercenaryEndYn;
    private String teamName;
}
