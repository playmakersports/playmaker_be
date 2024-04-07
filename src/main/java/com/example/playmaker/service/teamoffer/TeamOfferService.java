package com.example.playmaker.service.teamoffer;

import com.example.playmaker.domain.teamoffer.TeamOffer;
import com.example.playmaker.web.member.dto.OfferForm;

public interface TeamOfferService {

    TeamOffer offer(Long loginMemberId, Long offerMemberId, OfferForm offerForm);

}
