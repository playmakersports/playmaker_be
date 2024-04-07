package com.example.playmaker.domain.member;

import com.example.playmaker.TimeEntity;
import com.example.playmaker.code.ActiveArea;
import com.example.playmaker.code.Role;
import com.example.playmaker.code.Sex;
import com.example.playmaker.code.ActiveTime;
import com.example.playmaker.domain.mercenaryrecruitspc.MercenaryRecruitSpc;
import com.example.playmaker.domain.team.Team;
import com.example.playmaker.domain.teamoffer.TeamOffer;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;

import static javax.persistence.CascadeType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;
    private String userId;
    private String username;
    private String password;
    private String nickname;
    private String contact; //연락처
    private String birth;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    private String email;
    private String position;
    @Enumerated(value = EnumType.STRING)
    private ActiveArea activeArea;
    @Enumerated(value = EnumType.STRING)
    private ActiveTime activeTime;
    private String mercenaryYn;             //용병인지아닌지 구분
    private String proposalYn;              //영입제안허용여부
    private String gameStyle;
    private String selfIntro;
    private String preferredSoccerTeam;
    private String pfUrl;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(mappedBy = "member")
    @Cascade(value = {CascadeType.ALL})
    private TeamOffer teamOffer;

    @OneToOne(mappedBy = "member")
    @Cascade(value = {CascadeType.ALL})
    private MercenaryRecruitSpc recruitSpc;

    @Builder
    public Member(String userId ,String username, String password, String nickname, String contact, String birth, Sex sex, String email,
                  String position, ActiveArea activeArea, ActiveTime activeTime, String mercenaryYn, String proposalYn, String gameStyle,
                  String selfIntro, String preferredSoccerTeam, String pfUrl, Role role) {

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.contact = contact;
        this.birth = birth;
        this.sex = sex;
        this.email = email;
        this.position = position;
        this.activeArea = activeArea;
        this.activeTime = activeTime;
        this.mercenaryYn = mercenaryYn;
        this.proposalYn = proposalYn;
        this.gameStyle = gameStyle;
        this.selfIntro = selfIntro;
        this.preferredSoccerTeam = preferredSoccerTeam;
        this.pfUrl = pfUrl;
        this.role = role;
    }

}
