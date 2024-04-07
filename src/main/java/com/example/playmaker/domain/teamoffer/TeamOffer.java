package com.example.playmaker.domain.teamoffer;

import com.example.playmaker.TimeEntity;
import com.example.playmaker.domain.member.Member;
import com.example.playmaker.domain.team.Team;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamOffer extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_offer_id")
    private Long id;

    private String offerMsg;

    //영입제한을 할 팀
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    //영입제안을 받을 멤버
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public TeamOffer(String offerMsg, Team team, Member member) {
        this.offerMsg = offerMsg;
        this.team = team;
        this.member = member;
    }
}
