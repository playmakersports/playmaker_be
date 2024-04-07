package com.example.playmaker.service.teamboard;


import com.example.playmaker.code.Error;
import com.example.playmaker.domain.team.Team;
import com.example.playmaker.domain.team.TeamRepository;
import com.example.playmaker.domain.teamboard.TeamBoard;
import com.example.playmaker.domain.teamboard.TeamBoardRepository;
import com.example.playmaker.exception.CustomException;
import com.example.playmaker.service.file.FileService;
import com.example.playmaker.web.teamboard.dto.BoardForm;
import com.example.playmaker.web.teamboard.dto.BoardInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TeamBoardServiceImpl implements TeamBoardService{
    private final TeamBoardRepository teamBoardRepository;
    private final FileService fileService;
    private final TeamRepository teamRepository;

    @Override
    public void insertBoard(BoardForm boardForm, MultipartFile file) throws IOException {
        String path = fileService.fileUpload(file);
        Team team =  teamRepository.findById(boardForm.getTeamId()).orElse(null);
        TeamBoard board = TeamBoard.builder()
                .boardName(boardForm.getBoardName())
                .userName(boardForm.getUserName())
                .makeDt(boardForm.getMakeDt())
                .script(boardForm.getScript())
                .picUrl(path)
                .teamId(team)
                .build();
        teamBoardRepository.save(board);
    }

    @Override
    public List<BoardForm> selectAll() {
        List<TeamBoard> boardInfo = teamBoardRepository.findAll();
        List<BoardForm> info = boardInfo.stream()
                .map(o->new BoardForm())
                .sorted(Comparator.comparing(BoardForm::getMakeDt).reversed())
                .collect(Collectors.toList());
        return info;

    }

    @Override
    public BoardInfo findById(Long id) {
        TeamBoard board = teamBoardRepository.findById(id).orElseThrow(()-> new CustomException(Error.BOARD_NOT_FOUND));
        BoardInfo boardInfo = BoardInfo.builder()
                .boardName(board.getBoardName())
                .userName(board.getUserName())
                .makeDt(board.getMakeDt())
                .script(board.getScript())
                .picUrl(board.getPicUrl())
                .build();
        return boardInfo;
    }

    @Override
    public void editBoard(Long id, BoardForm boardForm, MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            String path = fileService.fileUpload(file);
            teamBoardRepository.findById(id).map(
                    entity -> entity.updateTeamBoard( boardForm.getBoardName()
                                                     , boardForm.getUserName()
                                                     , boardForm.getScript()
                                                        ,path)
            );
        }
        else {
            teamBoardRepository.findById(id).map(
                    entity -> entity.updateTeamBoard(boardForm.getBoardName()
                            , boardForm.getUserName()
                            , boardForm.getScript()
                            , boardForm.getPicUrl())
            );
        }
    }

    @Override
    public void deleteBoard(Long id) {
        TeamBoard board = teamBoardRepository.findById(id).orElseThrow(()-> new CustomException(Error.BOARD_NOT_FOUND));
        teamBoardRepository.delete(board);
    }
}
