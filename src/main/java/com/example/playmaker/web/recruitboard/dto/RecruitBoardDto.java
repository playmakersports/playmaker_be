package com.example.playmaker.web.recruitboard.dto;


import com.example.playmaker.code.Sex;
import com.example.playmaker.domain.team.Team;
import lombok.Data;

@Data
public class RecruitBoardDto {

    private Long teamId;
    private Sex sex;
    private String position;
    private Integer personNum;
    private String boardDate;
}
