package com.example.playmaker.domain.mercenaryrecruitspc;

import com.example.playmaker.TimeEntity;
import com.example.playmaker.domain.member.Member;
import com.example.playmaker.domain.mercenaryrecruitboard.MercenaryRecruitBoard;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MercenaryRecruitSpc extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String acceptYn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mercenary_recruit_board_id")
    private MercenaryRecruitBoard mercenaryRecruitBoard;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Builder
    private MercenaryRecruitSpc(MercenaryRecruitBoard mercenaryRecruitBoard, Member member){
        this.mercenaryRecruitBoard = mercenaryRecruitBoard;
        this.member = member;
    }
}
