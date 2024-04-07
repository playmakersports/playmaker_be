package com.example.playmaker.domain.mercenaryrecruitboard;

import com.example.playmaker.TimeEntity;
import com.example.playmaker.code.ActiveArea;
import com.example.playmaker.code.ActiveTime;
import com.example.playmaker.domain.mercenaryrecruitspc.MercenaryRecruitSpc;
import com.example.playmaker.domain.team.Team;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MercenaryRecruitBoard extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mercenary_recruit_board_id")
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private ActiveTime gameDt;
    @Enumerated(value = EnumType.STRING)
    private ActiveArea gameArea;
    private Integer recruitNum;
    private String mercenaryEndYn;

    // 한팀당 하나의 용병모집 게시글 게시 가능
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team mercenaryTeam;

    @OneToOne(mappedBy = "mercenaryRecruitBoard")
    @Cascade(value = {CascadeType.ALL})
    private MercenaryRecruitSpc recruitSpc;

    @Builder
    MercenaryRecruitBoard(ActiveTime activeTime, ActiveArea activeArea, Integer recruitNum,
                          String mercenaryEndYn, Team mercenaryTeam)
    {
        this.gameDt = activeTime;
        this.gameArea = activeArea;
        this.recruitNum = recruitNum;
        this.mercenaryEndYn = mercenaryEndYn;
        this.mercenaryTeam = mercenaryTeam;
    }

}
