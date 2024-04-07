package com.example.playmaker.web.teamboard.controller;


import com.example.playmaker.service.teamboard.TeamBoardService;
import com.example.playmaker.web.teamboard.dto.BoardForm;
import com.example.playmaker.web.teamboard.dto.BoardInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teamBoard")
@Slf4j
public class TeamBoardController {

    private final TeamBoardService teamBoardService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success")
            , @ApiResponse(responseCode = "400", description = "Bad Request")
            , @ApiResponse(responseCode = "404", description = "Not Found")
            , @ApiResponse(responseCode = "500", description = "Internal Error")
    })

    @ApiOperation(
            value = "팀 게시판 생성"
            , notes = "팀 게시판을 생성한다"
    )
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE
                            ,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> insertTeamBoard(@RequestPart(value = "boardInfo") BoardForm boardForm,
                                             @Parameter(description = "게시판 이미지") @RequestPart(value = "image") MultipartFile file) throws IOException{
        log.info("insert : boardDto", boardForm);
        teamBoardService.insertBoard(boardForm, file);
        return ResponseEntity.ok("success");
    }
    @GetMapping()
    public ResponseEntity<?> selectTeamBoard(){
        log.info("select : teamBoard");
        List<BoardForm> info = teamBoardService.selectAll();
        return ResponseEntity.ok(info);
    }
    @ApiOperation(
            value = "팀 게시판 조회"
            , notes = "팀 게시판을 조회한다"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> selectTeamBoard(@ApiParam(value = "팀PK",required = true) @PathVariable Long id)
    {
        log.info("select : teamBoard");
        BoardInfo info = teamBoardService.findById(id);
        return ResponseEntity.ok(info);
    }
    @ApiOperation(
            value = "팀 게시판 수정"
            , notes = "팀 게시판을 수정한다"
    )
    @PutMapping(value = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE
                                ,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> editTeamBoard(@ApiParam(value = "팀PK", required = true) @PathVariable Long id,
                                           @RequestPart(value = "boardInfo") BoardForm boardForm,
                                           @Parameter(description = "게시글 이미지") @RequestPart(value = "image") MultipartFile file) throws IOException{
        log.info("update : boardDto", boardForm);
        teamBoardService.editBoard(id, boardForm, file);
        return ResponseEntity.ok("success");
    }
    @ApiOperation(
            value = "팀 게시판 삭제"
            , notes = "팀 게시판을 삭제한다"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeamBoard(@ApiParam(value = "팀게시판PK",required = true) @PathVariable Long id)
    {
        log.info("delete : TeamBoard", id);
        teamBoardService.deleteBoard(id);
        return ResponseEntity.ok("success");
    }

}
