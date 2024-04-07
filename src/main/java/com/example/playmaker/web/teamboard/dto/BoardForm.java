package com.example.playmaker.web.teamboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Schema(title = "팀게시판")
public class BoardForm {
    @Schema(title = "게시판이름",example = "팀게시판1")
    private String BoardName;
    @Schema(title = "유저이름", example = "test")
    private String userName;
    @Schema(title = "만든날짜",example = "20230806")
    private String makeDt;
    @Schema(title = "게시판내용",example = "test")
    private String script;
    private String picUrl;
    @Schema(title = "팀PK",example = "1")
    private Long teamId;
}
