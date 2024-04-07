package com.example.playmaker.web.mercenaryrecruitboard.dto;

import com.example.playmaker.code.ActiveArea;
import com.example.playmaker.code.ActiveTime;
import lombok.Data;

@Data
public class MercenaryRecruitBoardForm {
    private ActiveTime gameDt;
    private ActiveArea gameArea;
    private Integer recruitNum;
    private String mercenaryEndYn;
    private Long teamId;
}
