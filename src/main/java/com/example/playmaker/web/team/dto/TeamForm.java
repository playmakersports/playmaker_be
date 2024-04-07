package com.example.playmaker.web.team.dto;

import com.example.playmaker.code.Item;
import com.example.playmaker.security.Token;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@Schema(title = "팀정보")
public class TeamForm {
    @Schema(title = "팀이름", example = "수원블루윙즈")
    private String teamName;
    @Schema(title = "팀영문이름", example = "suWonBlueWings")
    private String engName;
    @Schema(title = "팀컬러", example = "blue")
    private String teamColor;
    @Schema(title = "종목", example = "1")
    private Item item;
    @Schema(title = "창단날짜", example = "20230806")
    private String createDt;
    @Schema(title = "활동지역", example = "SEOUL")
    private String activeArea;
    private String logoUrl;
    @Schema(title = "팀소개", example = "안녕하세요 반갑습니다")
    private String teamIntro;
    @Schema(title = "자동가입여부", example = "Y")
    private String joinYn;
    @Schema(title = "가입메세지", example = "가입을 축하드립니다")
    private String message;
}
