package com.example.playmaker.service.boardcomment;


import com.example.playmaker.web.boardcomment.dto.CommentInfo;
import com.example.playmaker.web.boardcomment.dto.CommentForm;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BoardCommentService {
    void insertComment(CommentInfo commentDto);
    List<CommentInfo> selectAll();
    void editComment(CommentForm commentForm);
    void deleteComment(Long id);
}
