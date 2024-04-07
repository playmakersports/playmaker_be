package com.example.playmaker.web.mercenaryrecruitspc.controller;


import com.example.playmaker.service.mercenaryrecruitspc.MercenaryRecruitSpcService;
import com.example.playmaker.web.mercenaryrecruitspc.dto.MercenaryRecruitSpcForm;
import com.example.playmaker.web.mercenaryrecruitspc.dto.MercenaryRecruitSpcInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mercenaryRecruitSpc")
public class MercenaryRecruitSpcController {
    private final MercenaryRecruitSpcService mercenaryRecruitSpcService;

    @GetMapping()
    ResponseEntity<?> selectMercenaryRecruitSpc(){
        List<MercenaryRecruitSpcInfo> mercenaryRecruitSpcInfos = mercenaryRecruitSpcService.selectAll();
        log.info("select : mercenaryRecruitSpc");
        return ResponseEntity.ok(mercenaryRecruitSpcInfos);
    }

    @PostMapping()
    ResponseEntity<?> insertMercenaryRecruitSpc(@Valid @RequestBody MercenaryRecruitSpcForm mercenaryRecruitSpcForm) {
        mercenaryRecruitSpcService.insertMercenaryRecruitSpc(mercenaryRecruitSpcForm);
        log.info("insert : mercenaryRecruitSpc",mercenaryRecruitSpcForm);
        return ResponseEntity.ok("success");
    }

    @PutMapping("/{id}")
    ResponseEntity<?> deleteMercenaryRecruitSpc(@PathVariable Long id, @RequestBody MercenaryRecruitSpcForm mercenaryRecruitSpcForm){
        mercenaryRecruitSpcService.updateMercenaryRecruitSpc(id,mercenaryRecruitSpcForm);
        log.info("delete : mercenaryRecruitSpc",mercenaryRecruitSpcForm);
        return ResponseEntity.ok("success");
    }
}
