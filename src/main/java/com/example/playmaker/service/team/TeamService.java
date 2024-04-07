package com.example.playmaker.service.team;


import com.example.playmaker.web.team.dto.TeamForm;
import com.example.playmaker.web.team.dto.TeamInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public interface TeamService {

    void insertTeam(TeamForm teamDto, MultipartFile file) throws IOException;
    List<TeamInfo> selectTeam(String id);
    void editTeam(Long id, TeamForm teamForm, MultipartFile file) throws IOException;

}
