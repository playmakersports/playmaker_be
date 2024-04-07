package com.example.playmaker.web.member.dto;


import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class VaildForm {
    @NotBlank(message = "아이디는 필수값입니다")
    private String userId;
    @NotBlank(message =  "닉네임은 필수값입니다.")
    private String nickname;
    @NotBlank(message = "전화번호는 필수값입니다.")
    private String contact;
    @NotBlank(message = "이메일은 필수값입니다.")
    private String email;
}

