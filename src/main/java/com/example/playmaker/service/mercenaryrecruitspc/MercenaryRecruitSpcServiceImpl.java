package com.example.playmaker.service.mercenaryrecruitspc;

import com.example.playmaker.code.Error;
import com.example.playmaker.domain.member.Member;
import com.example.playmaker.domain.member.MemberRepository;
import com.example.playmaker.domain.mercenaryrecruitboard.MercenaryRecruitBoard;
import com.example.playmaker.domain.mercenaryrecruitboard.MercenaryRecruitBoardRepository;
import com.example.playmaker.domain.mercenaryrecruitspc.MercenaryRecruitSpc;
import com.example.playmaker.domain.mercenaryrecruitspc.MercenaryRecruitSpcRepository;
import com.example.playmaker.exception.CustomException;
import com.example.playmaker.web.mercenaryrecruitspc.dto.MercenaryRecruitSpcForm;
import com.example.playmaker.web.mercenaryrecruitspc.dto.MercenaryRecruitSpcInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MercenaryRecruitSpcServiceImpl implements MercenaryRecruitSpcService {
    private final MercenaryRecruitSpcRepository mercenaryRecruitSpcRepository;
    private final MemberRepository memberRepository;
    private final MercenaryRecruitBoardRepository recruitBoardRepository;
    @Override
    public List<MercenaryRecruitSpcInfo> selectAll() {
        List<MercenaryRecruitSpc> mercenaryRecruitSpcs = mercenaryRecruitSpcRepository.findAll();
        List<MercenaryRecruitSpcInfo> mercenaryRecruitSpcInfo = mercenaryRecruitSpcs.stream()
                .map( o -> new MercenaryRecruitSpcInfo())
                .filter(use->use.getAcceptYn() == null)
                .collect(Collectors.toList());
        return mercenaryRecruitSpcInfo;
    }
    @Override
    public void insertMercenaryRecruitSpc(MercenaryRecruitSpcForm mercenaryRecruitSpcForm) {
        Member member = memberRepository.findById(mercenaryRecruitSpcForm.getMemberId()).orElseThrow( ()-> new CustomException(Error.USER_NOT_FOUND));
        MercenaryRecruitBoard mercenaryRecruitBoard = recruitBoardRepository.findById(mercenaryRecruitSpcForm.getMercenaryRecruitBoardId()).orElseThrow( ()-> new CustomException(Error.BOARD_NOT_FOUND));
        MercenaryRecruitSpc recruitSpc = MercenaryRecruitSpc.builder()
                .mercenaryRecruitBoard(mercenaryRecruitBoard)
                .member(member)
                .build();
        mercenaryRecruitSpcRepository.save(recruitSpc);
    }

    @Transactional
    @Override
    public void updateMercenaryRecruitSpc(Long id, MercenaryRecruitSpcForm mercenaryRecruitSpcForm) {
        MercenaryRecruitSpc recruitSpc = mercenaryRecruitSpcRepository.findById(id).orElseThrow(()-> new CustomException(Error.BOARD_NOT_FOUND));
        recruitSpc.setAcceptYn(mercenaryRecruitSpcForm.getAcceptYn());
    }
}
