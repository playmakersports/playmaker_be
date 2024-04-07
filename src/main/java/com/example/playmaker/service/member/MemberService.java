package com.example.playmaker.service.member;


import com.example.playmaker.domain.member.Member;
import com.example.playmaker.domain.teamoffer.TeamOffer;
import com.example.playmaker.web.member.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MemberService {

    Member join(MemberForm memberForm);
    void valid(VaildForm vaildForm);
    LoginInfo login(LoginForm loginForm);
    UserPageInfo showUserPage(Long id);
    void updateUserPage(Long id, UserPageForm userPageForm, MultipartFile file) throws IOException;
    Member findMember(Long id);
}
