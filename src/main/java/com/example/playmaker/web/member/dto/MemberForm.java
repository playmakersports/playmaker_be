package com.example.playmaker.web.member.dto;

import com.example.playmaker.code.ActiveArea;
import com.example.playmaker.code.ActiveTime;
import com.example.playmaker.code.Sex;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberForm {

    @NotBlank(message = "아이디는 필수값입니다")
    private String userId;
    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;
    @NotBlank(message =  "닉네임은 필수값입니다.")
    private String nickname;
    @NotBlank(message = "이름은 필수값입니다.")
    private String username;
    private String contact;                 //연락처
    private String birth;
    private Sex sex;
    private String email;
    private String position;
    private ActiveArea activeArea;          //활동지역
    private ActiveTime activeTime;          //활동시간
    private String proposalYn;              //영입제안허용여부
    private String gameStyle;               //경기스타일 (해시태그)
    private String selfIntro;               //자기소개
    private String preferredSoccerTeam;     //선호하는 축구팀
    private String pfUrl;

}
