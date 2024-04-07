package com.example.playmaker.web.team.controller;


import com.example.playmaker.service.team.TeamService;
import com.example.playmaker.web.team.dto.TeamForm;
import com.example.playmaker.web.team.dto.TeamInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/team")
public class TeamController {

    private final TeamService teamService;

    @ApiResponses({
              @ApiResponse(responseCode = "200", description = "success")
            , @ApiResponse(responseCode = "400", description = "Bad Request")
            , @ApiResponse(responseCode = "404", description = "Not Found")
            , @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @ApiOperation(
            value = "팀 생성"
            , notes = "팀 데이터를 통해서 팀을 생성한다"
    )
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE
                ,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?>insertTeam(@RequestPart @Parameter(description = "팀정보") TeamForm teamDto,
                                       @RequestPart @Parameter(description = "팀이미지") MultipartFile file) throws IOException {
        log.info("insert : teamDto",teamDto);
        teamService.insertTeam(teamDto, file);
        return ResponseEntity.ok("success");
    }
    @ApiOperation(
            value = "팀 조회"
            , notes = "팀 데이터를 조회한다"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> selectTeam (@ApiParam(value = "팀Id",required = true) @PathVariable String id){
        log.info("select : team");
        List<TeamInfo> info = teamService.selectTeam(id);
        return ResponseEntity.ok(info);
    }
    @ApiOperation(
            value = "팀 수정"
            , notes = "팀 데이터를 수정한다"
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> editTeam(@ApiParam(value = "팀Id", required = true) @PathVariable Long id,
                                      @ApiParam(value = "팀정보", required = true) @RequestPart(value = "teamInfo") TeamForm teamDto,
                                      @ApiParam(value = "팀이미지") @RequestPart(value = "image") MultipartFile file) throws IOException{
        log.info("update : teamDto",teamDto);
        teamService.editTeam(id, teamDto, file);
        return ResponseEntity.ok("success");
    }

}
