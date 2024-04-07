package com.example.playmaker.service.recruitboard;

import com.example.playmaker.web.recruitboard.dto.RecruitBoardDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecruitBoardService {

    void insertRecruitBoard(RecruitBoardDto recruitBoard);
    List<RecruitBoardDto> selectAll();
    void deleteRecruitBoard(Long id);
}
