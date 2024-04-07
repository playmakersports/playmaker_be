package com.example.playmaker.domain.team;


import com.example.playmaker.TimeEntity;
import com.example.playmaker.code.Item;
import com.example.playmaker.domain.match.Match;
import com.example.playmaker.domain.member.Member;
import com.example.playmaker.domain.mercenaryrecruitboard.MercenaryRecruitBoard;
import com.example.playmaker.domain.teamoffer.TeamOffer;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.annotations.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Team extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long id;
    private String teamName;
    private String engName;
    private String teamColor;
    @Enumerated(value = EnumType.STRING)
    private Item item;
    private String createDt;
    private String activeArea;
    private String logoUrl;
    private String teamIntro;
    private String joinYn;
    private String message;

    // 박준순이 만듬
    @OneToMany(mappedBy = "team")
    @Cascade(value = {ALL})
    private List<Member> memberList = new ArrayList<>();

    // 박준순이 만듬
    @OneToMany(mappedBy = "team")
    @Cascade(value = {ALL})
    private List<TeamOffer> teamOfferList = new ArrayList<>();

    //박준순이 만듬
    @OneToOne(mappedBy = "homeTeam")
    @Cascade(value = {ALL})
    private Match matchHomeTeam;

    //박준순이 만듬
    @OneToOne(mappedBy = "awayTeam")
    @Cascade(value = {ALL})
    private Match matchAwayTeam;

    @OneToOne(mappedBy = "mercenaryTeam")
    @Cascade(value = {ALL})
    private MercenaryRecruitBoard mercenaryBoard;

    @Builder
    public Team(String teamName, String engName, String teamColor, Item item, String createDt,
                String activeArea, String logoUrl, String teamIntro, String joinYn, String message){
        this.teamName = teamName;
        this.engName = engName;
        this.teamColor = teamColor;
        this.item = item;
        this.createDt = createDt;
        this.activeArea = activeArea;
        this.logoUrl = logoUrl;
        this.teamIntro = teamIntro;
        this.joinYn = joinYn;
        this.message = message;
    }
    public Team updateTeam(String teamName, String engName, String teamColor, Item item, String createDt,
                           String activeArea, String logoUrl, String teamIntro, String joinYn, String message)
    {
        this.teamName = teamName;
        this.engName = engName;
        this.teamColor = teamColor;
        this.item = item;
        this.createDt  = createDt;
        this.activeArea = activeArea;
        this.logoUrl = logoUrl;
        this.teamIntro = teamIntro;
        this.joinYn = joinYn;
        this.message = message;

        return this;
    }
}
