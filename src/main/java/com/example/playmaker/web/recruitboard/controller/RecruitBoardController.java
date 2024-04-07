package com.example.playmaker.web.recruitboard.controller;


import com.example.playmaker.service.recruitboard.RecruitBoardService;
import com.example.playmaker.web.recruitboard.dto.RecruitBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recruitBoard")
public class RecruitBoardController {

    private final RecruitBoardService recruitBoardService;

    @PostMapping()
    public ResponseEntity<?> insertRecruitBoard (@Valid @RequestPart(value = "recruitInfo") RecruitBoardDto recruitBoardDto){

        log.info("insert : recruitBoardDto",recruitBoardDto);
        recruitBoardService.insertRecruitBoard(recruitBoardDto);
        return ResponseEntity.ok("success");
    }
    @GetMapping()
    public ResponseEntity<?> selectRecruitBoard(){
        log.info("select : recruitBoard");
        List<RecruitBoardDto> info = recruitBoardService.selectAll();
        return ResponseEntity.ok(info);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecruitBoard(@PathVariable Long id)
    {
        log.info("delete : recruitBoard");
        recruitBoardService.deleteRecruitBoard(id);
        return ResponseEntity.ok("success");
    }

}
