package com.example.playmaker.service.mercenaryrecruitboard;

import com.example.playmaker.code.Error;
import com.example.playmaker.domain.mercenaryrecruitboard.MercenaryRecruitBoard;
import com.example.playmaker.domain.mercenaryrecruitboard.MercenaryRecruitBoardRepository;
import com.example.playmaker.domain.team.Team;
import com.example.playmaker.domain.team.TeamRepository;
import com.example.playmaker.exception.CustomException;
import com.example.playmaker.web.mercenaryrecruitboard.dto.MercenaryRecruitBoardForm;
import com.example.playmaker.web.mercenaryrecruitboard.dto.MercenaryRecruitBoardInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MercenaryRecruitBoardServiceImpl implements MercenaryRecruitBoardService{

    private final MercenaryRecruitBoardRepository recruitBoardRepository;
    private final TeamRepository teamRepository;
    @Override
    public List<MercenaryRecruitBoardInfo> selectAll() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String time = now.format(formatter);
        List<MercenaryRecruitBoard> boardInfos = recruitBoardRepository.findAll();
        List<MercenaryRecruitBoardInfo> info = boardInfos.stream()
                .map(o-> new MercenaryRecruitBoardInfo())
                .filter(use -> Integer.parseInt(use.getGameDt()) < Integer.parseInt(time))
                .sorted(Comparator.comparing(MercenaryRecruitBoardInfo::getGameDt))
                .collect(Collectors.toList());
        return info;
    }

    @Transactional
    @Override
    public void insertMercenaryRecruitBoard(MercenaryRecruitBoardForm mercenaryRecruitBoardForm) {
        Team team = teamRepository.findById(mercenaryRecruitBoardForm.getTeamId()).orElseThrow(()-> new CustomException(Error.TEAM_NOT_FOUND));
        MercenaryRecruitBoard mercenaryRecruitBoard = MercenaryRecruitBoard.builder()
                .activeArea(mercenaryRecruitBoardForm.getGameArea()).activeTime(mercenaryRecruitBoardForm.getGameDt())
                .recruitNum(mercenaryRecruitBoardForm.getRecruitNum()).mercenaryEndYn(mercenaryRecruitBoardForm.getMercenaryEndYn())
                .mercenaryTeam(team).build();
        recruitBoardRepository.save(mercenaryRecruitBoard);

    }

    // delete로 두면 안될 것 같음.
    @Transactional
    @Override
    public void updateMercenaryRecruitBoard(Long id, MercenaryRecruitBoardForm mercenaryRecruitBoardForm) {
        MercenaryRecruitBoard recruitBoard = recruitBoardRepository.findById(id).orElseThrow(()-> new CustomException(Error.BOARD_NOT_FOUND));
        recruitBoardRepository.delete(recruitBoard);
    }
}
