package com.example.playmaker.web.mercenaryrecruitboard.controller;

import com.example.playmaker.service.mercenaryrecruitboard.MercenaryRecruitBoardService;
import com.example.playmaker.web.mercenaryrecruitboard.dto.MercenaryRecruitBoardForm;
import com.example.playmaker.web.mercenaryrecruitboard.dto.MercenaryRecruitBoardInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mercenaryRecruitBoard")
public class MercenaryRecruitBoardController {
    private final MercenaryRecruitBoardService mercenaryRecruitBoardService;

    @GetMapping()
    ResponseEntity<?> selectMercenaryRecruitBoard(){
        List<MercenaryRecruitBoardInfo> mercenaryRecruitBoardInfo = mercenaryRecruitBoardService.selectAll();
        log.info("select : mercenaryRecruitBoard");
        return ResponseEntity.ok(mercenaryRecruitBoardInfo);
    }
    @PostMapping()
    ResponseEntity<?> insertMercenaryRecruitBoard(@Valid @RequestBody MercenaryRecruitBoardForm mercenaryRecruitBoardForm)
    {
        mercenaryRecruitBoardService.insertMercenaryRecruitBoard(mercenaryRecruitBoardForm);
        log.info("insert : mercenaryRecruitBoard",mercenaryRecruitBoardForm);
        return ResponseEntity.ok("success");
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateMercenaryRecruitBoard(@PathVariable Long id, @RequestBody MercenaryRecruitBoardForm mercenaryRecruitBoardFrom){
        mercenaryRecruitBoardService.updateMercenaryRecruitBoard(id, mercenaryRecruitBoardFrom);
        log.info("update: mercenaryRecruitBoard",mercenaryRecruitBoardFrom);
        return ResponseEntity.ok("success");
    }

}
