package com.example.playmaker.web.member.controller;

import com.example.playmaker.security.PrincipalUserDetails;
import com.example.playmaker.service.member.MemberService;
import com.example.playmaker.service.teamoffer.TeamOfferService;
import com.example.playmaker.web.member.dto.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final TeamOfferService teamOfferService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success")
            , @ApiResponse(responseCode = "400", description = "Bad Request")
            , @ApiResponse(responseCode = "404", description = "Not Found")
            , @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @ApiOperation(
            value = "로그인"
            , notes = "로그인 방식 "
    )

    @PostMapping("/join")
    public ResponseEntity<String> join(@Valid @RequestBody @ApiParam(value = "회원가입정보") MemberForm memberForm) {
        memberService.join(memberForm);
        //return new ResponseEntity<>("success", HttpStatus.OK);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validation")
    public ResponseEntity<String> valid(@Valid @RequestBody @ApiParam(value = "회원가입정보검증체크") VaildForm vaildForm)
    {
        memberService.valid(vaildForm);
        //return new ResponseEntity<>("success", HttpStatus.OK);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginInfo> login(@Valid @RequestBody @ApiParam(value = "로그인 데이터") LoginForm loginForm) {
        return new ResponseEntity<>(memberService.login(loginForm), HttpStatus.OK);   //jwt 내부 id, username, role 들어감
    }


    //UserPage 쪽은 로그인자의 id와 userPage 주인의 id를 구분해야함
    //로그인자의 id는 jwt 안에 포함되있음.
    //userPage 주인의 id는 pathVariable 임
    @ApiOperation(
            value = "개인정보 페이지 정보"
            , notes = "개인정보 데이터 조회"
    )
    @GetMapping("/userPage/{memberId}")
    public ResponseEntity<UserPageInfo> showUserPage(@PathVariable @ApiParam(value = "조회하고자 하는 회원의 기본값") Long memberId) {
        return new ResponseEntity<>(memberService.showUserPage(memberId), HttpStatus.OK);
    }
    @ApiOperation(
            value = "개인정보 페이지 수정"
            , notes = "개인정보 수정"
    )
    @GetMapping("/userPage/{memberId}/edit")
    public ResponseEntity<UserPageInfo> showEditUserPage(@PathVariable @ApiParam(value = "수정할 개인 데이터") Long memberId) {
        return new ResponseEntity<>(memberService.showUserPage(memberId), HttpStatus.OK);
    }
    @ApiOperation(
            value = "개인정보 페이지 수정"
            , notes = "개인정보 수정"
    )
    @PostMapping("/userPage/{memberId}/edit")
    public ResponseEntity<?> updateEditUserPage(@RequestPart @ApiParam(value = "userPageForm") UserPageForm userPageForm,
                                                @RequestPart(value = "image") MultipartFile file,
                                                @PathVariable Long memberId) throws IOException {
        memberService.updateUserPage(memberId, userPageForm, file);
        return ResponseEntity.ok().build();
        //return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @ApiOperation(
            value = "팀 입단 요청 확인 페이지"
            , notes = "팀 입단 요청을 받은 것을 확인"
    )
    @PostMapping("/userPage/{memberId}/offer")
    public ResponseEntity<?> showOfferPage(@AuthenticationPrincipal PrincipalUserDetails principalUserDetails, @PathVariable @ApiParam(value = "개인 기본값") Long memberId,
                                           @RequestBody @ApiParam(value = "팀제안") OfferForm offerForm) {

        Long loginMemberId = principalUserDetails.getMember().getId();
        teamOfferService.offer(loginMemberId, memberId, offerForm);
        return ResponseEntity.ok().build();
        //return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
