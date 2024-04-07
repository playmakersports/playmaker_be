package com.example.playmaker.web.match.controller;

import com.example.playmaker.security.PrincipalUserDetails;
import com.example.playmaker.service.match.MatchService;
import com.example.playmaker.web.match.dto.MatchForm;
import com.example.playmaker.web.match.dto.MatchInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/match")
    public ResponseEntity<String> createMatch(@AuthenticationPrincipal PrincipalUserDetails principalUserDetails,
                                              @Valid @RequestBody MatchForm matchForm) {

        Long loginMemberId = principalUserDetails.getMember().getId();
        matchService.createMatch(loginMemberId, matchForm);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/match")
    public ResponseEntity<MatchInfo> showBeforeMatch(@AuthenticationPrincipal PrincipalUserDetails principalUserDetails) {
        Long loginMemberId = principalUserDetails.getMember().getId();
        return new ResponseEntity<>(matchService.showBeforeMatch(loginMemberId), HttpStatus.OK);
    }

    @GetMapping(value ={"/match/request/{matchId}", "/match/{matchId}"})
    public ResponseEntity<MatchInfo> showMatch(@PathVariable Long matchId) {
        return new ResponseEntity<>(matchService.showMatch(matchId), HttpStatus.OK);
    }



}
