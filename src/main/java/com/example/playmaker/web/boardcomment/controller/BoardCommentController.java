package com.example.playmaker.web.boardcomment.controller;


import com.example.playmaker.service.boardcomment.BoardCommentService;
import com.example.playmaker.web.boardcomment.dto.CommentInfo;
import com.example.playmaker.web.boardcomment.dto.CommentForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@Slf4j
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @PostMapping()
    public ResponseEntity<?> insertComment(@Valid @RequestBody CommentInfo commentDto){
        boardCommentService.insertComment(commentDto);
        return ResponseEntity.ok("success");
    }

    @GetMapping()
    public ResponseEntity<?> selectComment(){
        List<CommentInfo> info = boardCommentService.selectAll();
        return ResponseEntity.ok(info);
    }

    @PutMapping()
    public ResponseEntity<?> editComment(@Valid @RequestBody CommentForm commentForm){
        boardCommentService.editComment(commentForm);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@Valid @PathVariable Long id){
        boardCommentService.deleteComment(id);
        return ResponseEntity.ok("success");
    }

}
