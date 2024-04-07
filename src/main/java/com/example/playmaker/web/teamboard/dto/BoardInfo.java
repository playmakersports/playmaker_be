package com.example.playmaker.web.teamboard.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class BoardInfo {

    private String BoardName;
    private String userName;
    private String makeDt;
    private String script;
    private String picUrl;

    @Builder
    private BoardInfo(String boardName, String userName, String makeDt, String script, String picUrl){
        this.BoardName = boardName;
        this.userName = userName;
        this.makeDt = makeDt;
        this.script = script;
        this.picUrl = picUrl;
    }

}
