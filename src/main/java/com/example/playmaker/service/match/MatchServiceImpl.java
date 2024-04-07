package com.example.playmaker.service.match;

import com.example.playmaker.code.Error;
import com.example.playmaker.code.PrgStatusCode;
import com.example.playmaker.domain.match.Match;
import com.example.playmaker.domain.match.MatchRepository;
import com.example.playmaker.domain.member.Member;
import com.example.playmaker.domain.member.MemberRepository;
import com.example.playmaker.domain.team.Team;
import com.example.playmaker.domain.team.TeamRepository;
import com.example.playmaker.exception.CustomException;
import com.example.playmaker.web.match.dto.MatchForm;
import com.example.playmaker.web.match.dto.MatchInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.playmaker.code.Error.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchServiceImpl implements MatchService{

    private final MatchRepository matchRepository;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @Override
    @Transactional
    public Match createMatch(Long id, MatchForm matchForm) {
        Member loginMember = memberRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Team awayTeam = teamRepository.findById(matchForm.getAwayTeamId()).orElseThrow(() -> new CustomException(TEAM_NOT_FOUND));
        Team homeTeam = loginMember.getTeam();

        Match match = Match.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .matchDt(matchForm.getMatchDt())
                .matchArea(matchForm.getMatchArea())
                .publicMatchYn(matchForm.getPublicMatchYn())
                .prgStatusCode(PrgStatusCode.BEFORE)
                //.resultCode()
                //.homeTeamPlayerRegisterYn()
                .build();
        return matchRepository.save(match);
    }

    @Override
    public MatchInfo showBeforeMatch(Long id) {
        Member loginMember = memberRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Team homeTeam = loginMember.getTeam();

        return MatchInfo.builder()
                .homeTeamName(homeTeam.getTeamName())
                //.awayTeamName(null)
                //.matchDt(null)
                //.matchArea(null)
                .build();
    }

    @Override
    public MatchInfo showMatch(Long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new CustomException(MATCH_NOT_FOUND));

        return MatchInfo.builder()
                .homeTeamName(match.getHomeTeam().getTeamName())
                .awayTeamName(match.getAwayTeam().getTeamName())
                .matchDt(match.getMatchDt())
                .matchArea(match.getMatchArea())
                .build();
    }
}
