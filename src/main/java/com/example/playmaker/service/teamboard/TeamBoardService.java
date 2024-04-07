package com.example.playmaker.service.teamboard;

import com.example.playmaker.web.teamboard.dto.BoardForm;
import com.example.playmaker.web.teamboard.dto.BoardInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public interface TeamBoardService{

    void insertBoard(BoardForm boardForm, MultipartFile file) throws IOException;
    List<BoardForm> selectAll();
    BoardInfo findById(Long id);
    void editBoard(Long id, BoardForm boardForm, MultipartFile file) throws IOException;
    void deleteBoard(Long id);
}
