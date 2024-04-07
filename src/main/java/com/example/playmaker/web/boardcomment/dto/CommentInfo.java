package com.example.playmaker.web.boardcomment.dto;

import lombok.Data;

@Data
public class CommentInfo {

    private String username;
    private String makeDt;
    private String content;
    private Long boardId;
}
