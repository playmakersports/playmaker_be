package com.example.playmaker.service.recruitspc;

import com.example.playmaker.code.Error;
import com.example.playmaker.domain.member.Member;
import com.example.playmaker.domain.member.MemberRepository;
import com.example.playmaker.domain.recruitboard.RecruitBoard;
import com.example.playmaker.domain.recruitboard.RecruitBoardRepository;
import com.example.playmaker.domain.recruitspc.RecruitSpc;
import com.example.playmaker.domain.recruitspc.RecruitSpcRepository;
import com.example.playmaker.exception.CustomException;
import com.example.playmaker.web.recruitspc.dto.RecruitSpcForm;
import com.example.playmaker.web.recruitspc.dto.RecruitSpcInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitSpcServiceImpl implements RecruitSpcService{
    private final RecruitBoardRepository recruitBoardRepository;
    private final MemberRepository memberRepository;
    private final RecruitSpcRepository recruitSpcRepository;
    @Override
    public void insertRecruitSpc(RecruitSpcForm recruitSpcForm) {
        RecruitBoard board = recruitBoardRepository.findById(recruitSpcForm.getRecruitBoard()).orElseThrow(() -> new CustomException(Error.BOARD_NOT_FOUND));
        Member member = memberRepository.findById(recruitSpcForm.getMemberId()).orElseThrow(() -> new CustomException(Error.USER_NOT_FOUND));
        RecruitSpc recruitSpc = RecruitSpc.builder()
                .join_yn(recruitSpcForm.getJoinYn())
                .recruitBoard(board)
                .memberId(member)
                .build();
        recruitSpcRepository.save(recruitSpc);
    }

    @Override
    public List<RecruitSpcInfo> selectAll() {
        List<RecruitSpc> recruitSpcInfos = recruitSpcRepository.findAll();
        List<RecruitSpcInfo> info = new ArrayList<>();
        for(int i=0;i<=recruitSpcInfos.size();i++)
        {
            Member member = recruitSpcInfos.get(i).getMemberId();
            RecruitSpcInfo recruitSpcinfo = RecruitSpcInfo.builder()
                    .nickname(member.getNickname())
                    .birth(member.getBirth())
                    .position(member.getPosition())
                    .gameStyle(member.getGameStyle())
                    .selfIntro(member.getSelfIntro())
                    .joinYn(recruitSpcInfos.get(i).getJoin_yn())
                    .build();
            info.add(recruitSpcinfo);
        }
        return info;
    }

    @Override
    public void updateRecruitSpc(Long id, RecruitSpcForm recruitSpcForm) {
        RecruitSpc recruitSpc = recruitSpcRepository.findById(id).orElseThrow(()-> new CustomException(Error.USER_NOT_FOUND));
        recruitSpc.setJoin_yn(recruitSpcForm.getJoinYn());
    }
}
