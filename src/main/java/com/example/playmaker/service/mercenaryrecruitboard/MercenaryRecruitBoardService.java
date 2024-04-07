package com.example.playmaker.service.mercenaryrecruitboard;

import com.example.playmaker.web.mercenaryrecruitboard.dto.MercenaryRecruitBoardForm;
import com.example.playmaker.web.mercenaryrecruitboard.dto.MercenaryRecruitBoardInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MercenaryRecruitBoardService {
    List<MercenaryRecruitBoardInfo> selectAll();
    void insertMercenaryRecruitBoard(MercenaryRecruitBoardForm mercenaryRecruitBoardForm);
    void updateMercenaryRecruitBoard(Long id, MercenaryRecruitBoardForm mercenaryRecruitBoardForm);
}
