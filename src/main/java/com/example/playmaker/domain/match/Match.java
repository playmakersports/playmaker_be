package com.example.playmaker.domain.match;

import com.example.playmaker.TimeEntity;
import com.example.playmaker.code.PrgStatusCode;
import com.example.playmaker.code.ResultCode;
import com.example.playmaker.domain.team.Team;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "match_id")
    private Long id;
    private String matchDt;
    private String matchArea;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;


    private String publicMatchYn;
    private int homeTeamScore;
    private int awayTeamScore;
    @Enumerated(value = EnumType.STRING)
    private PrgStatusCode prgStatusCode;
    @Enumerated(value = EnumType.STRING)
    private ResultCode resultCode;
    private String homeTeamPlayerRegisterYn;
    private String awayTeamPlayerRegisterYn;
    private String matchEndYn;

    @Builder
    public Match(String matchDt, String matchArea, Team homeTeam, Team awayTeam, String publicMatchYn, int homeTeamScore,
                 int awayTeamScore, PrgStatusCode prgStatusCode, ResultCode resultCode, String homeTeamPlayerRegisterYn,
                 String awayTeamPlayerRegisterYn, String matchEndYn) {

        this.matchDt = matchDt;
        this.matchArea = matchArea;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.publicMatchYn = publicMatchYn;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.prgStatusCode = prgStatusCode;
        this.resultCode = resultCode;
        this.homeTeamPlayerRegisterYn = homeTeamPlayerRegisterYn;
        this.awayTeamPlayerRegisterYn = awayTeamPlayerRegisterYn;
        this.matchEndYn = matchEndYn;

    }
}
