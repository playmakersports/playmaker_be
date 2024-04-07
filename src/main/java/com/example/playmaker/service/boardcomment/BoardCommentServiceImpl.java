package com.example.playmaker.service.boardcomment;


import com.example.playmaker.code.Error;
import com.example.playmaker.domain.boardcomment.BoardComment;
import com.example.playmaker.domain.boardcomment.BoardCommentRepository;
import com.example.playmaker.domain.teamboard.TeamBoard;
import com.example.playmaker.domain.teamboard.TeamBoardRepository;
import com.example.playmaker.exception.CustomException;
import com.example.playmaker.web.boardcomment.dto.CommentInfo;
import com.example.playmaker.web.boardcomment.dto.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService{

    private final BoardCommentRepository boardCommentRepository;
    private final TeamBoardRepository teamBoardRepository;
    @Override
    public void insertComment(CommentInfo commentDto) {
        TeamBoard board = teamBoardRepository.findById(commentDto.getBoardId()).orElseThrow(()-> new CustomException(Error.BOARD_NOT_FOUND));
        BoardComment boardComment = BoardComment.builder()
                .userName(commentDto.getUsername())
                .makeDt(commentDto.getMakeDt())
                .content(commentDto.getContent())
                .board(board)
                .build();
        boardCommentRepository.save(boardComment);
    }

    @Override
    public List<CommentInfo> selectAll() {
        List<BoardComment> boardInfo = boardCommentRepository.findAll();
        List<CommentInfo> info = boardInfo.stream()
                .map(o-> new CommentInfo())
                .sorted(Comparator.comparing(CommentInfo::getMakeDt).reversed())
                .collect(Collectors.toList());
        return info;
    }

    @Override
    @Transactional
    public void editComment(CommentForm commentForm) {
        BoardComment boardComment = boardCommentRepository.findById(commentForm.getCommentId()).orElseThrow(()-> new CustomException(Error.COMMENT_NOT_FOUND));
        boardComment.setContent(commentForm.getContent());
    }

    @Override
    public void deleteComment(Long id) {
        BoardComment boardComment = boardCommentRepository.findById(id).orElseThrow(()-> new CustomException(Error.COMMENT_NOT_FOUND));
        boardCommentRepository.delete(boardComment);
    }
}
