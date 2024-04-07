package com.example.playmaker.service.teamoffer;

import com.example.playmaker.domain.member.Member;
import com.example.playmaker.domain.member.MemberRepository;
import com.example.playmaker.domain.team.Team;
import com.example.playmaker.domain.teamoffer.TeamOffer;
import com.example.playmaker.domain.teamoffer.TeamOfferRepository;
import com.example.playmaker.exception.CustomException;
import com.example.playmaker.web.member.dto.OfferForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.playmaker.code.Error.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamOfferServiceImpl implements TeamOfferService{

    private final TeamOfferRepository teamOfferRepository;
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public TeamOffer offer(Long loginMemberId, Long offerMemberId, OfferForm offerForm) {
        Member loginMember = memberRepository.findById(loginMemberId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Team team = loginMember.getTeam();
        Member offerMember = memberRepository.findById(offerMemberId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        TeamOffer offer = TeamOffer.builder()
                .offerMsg(offerForm.getOfferMsg())
                .team(team)
                .member(offerMember)
                .build();

        return teamOfferRepository.save(offer);
    }
}
