package com.example.playmaker.web.recruitspc.controller;


import com.example.playmaker.service.recruitspc.RecruitSpcService;
import com.example.playmaker.web.recruitspc.dto.RecruitSpcForm;
import com.example.playmaker.web.recruitspc.dto.RecruitSpcInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruitSpc")
public class RecruitSpcController {

    private final RecruitSpcService recruitSpcService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success")
            , @ApiResponse(responseCode = "400", description = "Bad Request")
            , @ApiResponse(responseCode = "404", description = "Not Found")
            , @ApiResponse(responseCode = "500", description = "Internal Error")
    })

    @ApiOperation(
            value = "용병 모집"
            , notes = "용병 모집"
    )
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> insertRecruitSpc(@RequestBody RecruitSpcForm recruitSpcForm)
    {
        log.info("insert : recruitSpcDto", recruitSpcForm);
        recruitSpcService.insertRecruitSpc(recruitSpcForm);
        return ResponseEntity.ok("success");
    }
    @ApiOperation(
            value = "모집 용벙 전체 조회"
            , notes = "용병 데이터를 조회한다."
    )
    @GetMapping()
    public ResponseEntity<?> selectRecruitSpc(){
        List<RecruitSpcInfo> info = recruitSpcService.selectAll();
        log.info("select : recruitSpc");
        return ResponseEntity.ok(info);
    }
    @ApiOperation(
            value = "모집 용병 삭제"
            , notes = "용병 데이터 삭제"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> updateRecruitSpc(@PathVariable Long id, @ApiParam(value = "용병삭제", required = true) @RequestBody  RecruitSpcForm recruitSpcForm){
        recruitSpcService.updateRecruitSpc(id,recruitSpcForm);
        log.info("delete : recruitSpcDto");
        return ResponseEntity.ok("success");
    }
}
