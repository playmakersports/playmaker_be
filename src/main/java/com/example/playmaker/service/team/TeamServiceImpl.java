package com.example.playmaker.service.team;

import com.example.playmaker.domain.team.Team;
import com.example.playmaker.domain.team.TeamRepository;
import com.example.playmaker.exception.CustomException;
import com.example.playmaker.service.file.FileService;
import com.example.playmaker.web.team.dto.TeamForm;
import com.example.playmaker.web.team.dto.TeamInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.playmaker.code.Error.TEAM_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepository;
    private final FileService fileService;

    @Override
    public void insertTeam(TeamForm teamDto, MultipartFile file) throws IOException {
        String path = fileService.fileUpload(file);
        Team team = Team.builder()
                .teamName(teamDto.getTeamName())
                .engName(teamDto.getEngName())
                .teamColor(teamDto.getTeamColor())
                .item(teamDto.getItem())
                .activeArea(teamDto.getActiveArea())
                .logoUrl(path)
                .teamIntro(teamDto.getTeamIntro())
                .joinYn(teamDto.getJoinYn())
                .message(teamDto.getMessage())
                .build();
        teamRepository.save(team);
    }

    @Override
    public List<TeamInfo> selectTeam(String id) {
        List<TeamInfo> listInfo = new ArrayList<TeamInfo>();
        if (id.length() == 0)
        {
            List<Team> teamInfo = teamRepository.findAll();
            listInfo = teamInfo.stream()
                    .map(o -> TeamInfo.builder()
                            .teamName(o.getTeamName())
                            .engName(o.getEngName())
                            .teamColor(o.getTeamColor())
                            .item(o.getItem())
                            .createDt(o.getCreateDt())
                            .activeArea(o.getActiveArea())
                            .logoUrl(o.getLogoUrl())
                            .teamIntro(o.getTeamIntro())
                            .joinYn(o.getJoinYn())
                            .message(o.getMessage()).build())
                    .sorted(Comparator.comparing(TeamInfo::getCreateDt).reversed())
                    .collect(Collectors.toList());
        }
        else{
            Long longId = Long.parseLong(id);
            Team info = teamRepository.findById(longId).orElseThrow(() -> new CustomException(TEAM_NOT_FOUND));
            TeamInfo teamInfo = TeamInfo.builder()
                                        .teamName(info.getTeamName())
                                        .engName(info.getEngName())
                                        .teamColor(info.getTeamColor())
                                        .item(info.getItem())
                                        .createDt(info.getCreateDt())
                                        .activeArea(info.getActiveArea())
                                        .logoUrl(info.getLogoUrl())
                                        .teamIntro(info.getTeamIntro())
                                        .joinYn(info.getJoinYn())
                                        .message(info.getMessage())
                                        .build();
            listInfo.add(teamInfo);
        }
        return listInfo;
    }

    @Override
    public void editTeam(Long id, TeamForm teamForm, MultipartFile file) throws IOException {
        String path = fileService.fileUpload(file);
        teamRepository.findById(id).map(
                entity -> entity.updateTeam(teamForm.getTeamName()
                                         ,teamForm.getEngName()
                                         ,teamForm.getTeamColor()
                                         ,teamForm.getItem()
                                         ,teamForm.getCreateDt()
                                         ,teamForm.getActiveArea()
                                         ,path
                                         ,teamForm.getTeamIntro()
                                         ,teamForm.getJoinYn()
                                         ,teamForm.getMessage())).orElseThrow(() -> new CustomException(TEAM_NOT_FOUND)
        );
    }
}
