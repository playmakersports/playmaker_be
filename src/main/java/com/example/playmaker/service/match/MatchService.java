package com.example.playmaker.service.match;

import com.example.playmaker.domain.match.Match;
import com.example.playmaker.web.match.dto.MatchForm;
import com.example.playmaker.web.match.dto.MatchInfo;

public interface MatchService {

    Match createMatch(Long loginMemberId, MatchForm matchForm);
    MatchInfo showBeforeMatch(Long loginMemberId);
    MatchInfo showMatch(Long matchId);



}
