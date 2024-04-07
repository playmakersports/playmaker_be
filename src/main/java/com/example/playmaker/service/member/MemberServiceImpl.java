package com.example.playmaker.service.member;

import com.example.playmaker.code.ActiveArea;
import com.example.playmaker.code.ActiveTime;
import com.example.playmaker.code.Role;
import com.example.playmaker.domain.member.Member;
import com.example.playmaker.domain.member.MemberRepository;
import com.example.playmaker.domain.recruitboard.RecruitBoard;
import com.example.playmaker.domain.team.Team;
import com.example.playmaker.domain.teamoffer.TeamOffer;
import com.example.playmaker.domain.teamoffer.TeamOfferRepository;
import com.example.playmaker.exception.CustomException;
import com.example.playmaker.security.JwtTokenProvider;
import com.example.playmaker.service.file.FileService;
import com.example.playmaker.web.member.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.util.Optional;

import static com.example.playmaker.code.Error.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final FileService fileService;

    @Transactional
    @Override
    public Member join(MemberForm memberForm) {


        validateUsername(memberForm.getUsername()); // 아이디중복검증
        validateNickname(memberForm.getNickname()); // 닉네임중복검증
        validateEmail(memberForm.getEmail()); //이메일중복검증

        Member member = Member.builder()
                .userId(memberForm.getUserId())
                .username(memberForm.getUsername())
                .password(passwordEncoder.encode(memberForm.getPassword()))
                .nickname(memberForm.getNickname())
                .contact(memberForm.getContact())
                .birth(memberForm.getBirth())
                .sex(memberForm.getSex())
                .email(memberForm.getEmail())
                .position(memberForm.getPosition())
                .activeArea(memberForm.getActiveArea())
                .activeTime(memberForm.getActiveTime())
                .mercenaryYn("N")  //회원가입시 용병여부 디폴트값 N
                .proposalYn(memberForm.getProposalYn())
                .gameStyle(memberForm.getGameStyle())
                .selfIntro(memberForm.getSelfIntro())
                .preferredSoccerTeam(memberForm.getPreferredSoccerTeam())
                .pfUrl(memberForm.getPfUrl())
                .role(Role.ROLE_PLAYER) //최초가입시 일반 선수로 역할지정
                .build();
        return memberRepository.save(member);
    }

    @Transactional
    @Override
    public void valid(VaildForm vaildForm) {
        validateUsername(vaildForm.getUserId()); // 아이디중복검증
        validateNickname(vaildForm.getNickname()); // 닉네임중복검증
        validateContact(vaildForm.getContact()); // 전화번호중복검증
        validateEmail(vaildForm.getEmail()); //이메일중복검증
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

    }
    @Transactional
    @Override
    public LoginInfo login(LoginForm loginForm) {
        Member member = memberRepository.findByUsername(loginForm.getUsername())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if (!passwordEncoder.matches(loginForm.getPassword(), member.getPassword())) {
            throw new CustomException(MISMATCH_PASSWORD);
        }

        return LoginInfo.builder()
                .username(member.getUsername())
                .nickname(member.getNickname())
                .token(jwtTokenProvider.generateToken(member.getId(), member.getUsername(), member.getRole()))
                .build();
    }

    //누적골, 동네최고순위, MOM횟수 추가해야함
    @Override
    public UserPageInfo showUserPage(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return UserPageInfo.builder()
                .nickname(findMember.getNickname())
                .pfUrl(findMember.getPfUrl())
                .birth(findMember.getBirth())
                .email(findMember.getEmail())
                .contact(findMember.getContact())
                .position(findMember.getPosition())
                .gameStyle(findMember.getGameStyle())
                .selfIntro(findMember.getSelfIntro())
                .preferredSoccerTeam(findMember.getPreferredSoccerTeam())
                .activeArea(findMember.getActiveArea().getValue())
                .activeTime(findMember.getActiveTime().getValue())
                .proposalYn(findMember.getProposalYn())
                .build();
    }

    @Transactional
    @Override
    public void updateUserPage(Long id, UserPageForm userPageForm, MultipartFile file) throws IOException {
        String path = fileService.fileUpload(file);

        Member findMember = memberRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        findMember.setNickname(userPageForm.getNickname());
        findMember.setPfUrl(userPageForm.getPfUrl());
        findMember.setContact(userPageForm.getContact());
        findMember.setPosition(userPageForm.getPosition());
        findMember.setGameStyle(userPageForm.getGameStyle());
        findMember.setSelfIntro(userPageForm.getSelfIntro());
        findMember.setPreferredSoccerTeam(userPageForm.getPreferredSoccerTeam());
        findMember.setActiveArea(ActiveArea.of(Long.parseLong(userPageForm.getActiveArea())));
        findMember.setActiveTime(ActiveTime.of(userPageForm.getActiveTime()));
        findMember.setProposalYn(userPageForm.getProposalYn());
        findMember.setPfUrl(path);
    }


    private void validateUsername(String userId) {
        if (memberRepository.existsByUserId(userId)) {
            throw new CustomException(DUPLICATE_USERID);
        }
    }

    private void validateNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new CustomException(DUPLICATE_NICKNAME);
        }
    }

    private void validateContact(String contact){
        if (memberRepository.existsByContact(contact)){
            throw new CustomException(DUPLICATE_CONTACT);
        }
    }

    private void validateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new CustomException(DUPLICATE_EMAIL);
        }
    }

}
