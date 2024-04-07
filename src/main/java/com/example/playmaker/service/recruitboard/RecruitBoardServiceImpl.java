package com.example.playmaker.service.recruitboard;

import com.example.playmaker.code.Error;
import com.example.playmaker.domain.recruitboard.RecruitBoard;
import com.example.playmaker.domain.recruitboard.RecruitBoardRepository;
import com.example.playmaker.domain.team.Team;
import com.example.playmaker.domain.team.TeamRepository;
import com.example.playmaker.exception.CustomException;
import com.example.playmaker.service.team.TeamService;
import com.example.playmaker.web.recruitboard.dto.RecruitBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecruitBoardServiceImpl implements RecruitBoardService {

    private final TeamRepository teamRepository;
    private final RecruitBoardRepository recruitBoardRepository;
    @Override
    public void insertRecruitBoard(RecruitBoardDto recruitBoard) {
        Team team = teamRepository.findById(recruitBoard.getTeamId()).orElseThrow(()-> new CustomException(Error.TEAM_NOT_FOUND));
        RecruitBoard board = RecruitBoard.builder()
                .team(team)
                .sex(recruitBoard.getSex())
                .position(recruitBoard.getPosition())
                .personNum(recruitBoard.getPersonNum())
                .boardDate(recruitBoard.getBoardDate())
                .build();
        recruitBoardRepository.save(board);

    }

    @Override
    public List<RecruitBoardDto> selectAll() {
        List<RecruitBoard> recruitInfo = recruitBoardRepository.findAll();
        List<RecruitBoardDto> info = recruitInfo.stream()
                .map(o-> new RecruitBoardDto())
                .sorted(Comparator.comparing(RecruitBoardDto::getBoardDate).reversed())
                .collect(Collectors.toList());
        return info;
    }

    @Override
    public void deleteRecruitBoard(Long id) {
        RecruitBoard board = recruitBoardRepository.findById(id).orElseThrow(() -> new CustomException(Error.USER_NOT_FOUND));
        recruitBoardRepository.delete(board);
    }
}
