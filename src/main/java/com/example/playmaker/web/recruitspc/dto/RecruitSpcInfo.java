package com.example.playmaker.web.recruitspc.dto;

import com.example.playmaker.domain.member.Member;
import com.example.playmaker.domain.recruitboard.RecruitBoard;
import lombok.Builder;
import lombok.Data;

@Data
public class RecruitSpcInfo {
    private String nickname;
    private String birth;
    private String position;
    private String gameStyle;
    private String selfIntro;
    private String joinYn;
    @Builder
    private RecruitSpcInfo(String nickname, String birth, String position, String gameStyle,
                            String selfIntro, String joinYn){
        this.nickname = nickname;
        this.birth = birth;
        this.position = position;
        this.gameStyle = gameStyle;
        this.selfIntro = selfIntro;
        this.joinYn = joinYn;
    }
}
