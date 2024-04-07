package com.example.playmaker.domain.recruitboard;

import com.example.playmaker.TimeEntity;
import com.example.playmaker.code.Sex;
import com.example.playmaker.domain.team.Team;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitBoard extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="recruit_id")
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    private String position;
    private Integer personNum;
    private String boardDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Team teamId;

    @Builder
    public RecruitBoard(Sex sex, String position, Integer personNum, String boardDate, Team team)
    {
        this.sex = sex;
        this.position=position;
        this.personNum = personNum;
        this.boardDate = boardDate;
        this.teamId = team;
    }

}
