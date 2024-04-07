package com.example.playmaker.domain.recruitspc;

import com.example.playmaker.TimeEntity;
import com.example.playmaker.domain.member.Member;
import com.example.playmaker.domain.recruitboard.RecruitBoard;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitSpc extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "recruit_spc_id")
    private Long id;
    private String join_yn;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member memberId;
    @ManyToOne(fetch = FetchType.LAZY)
    private RecruitBoard recruitBoard;

    @Builder
    public RecruitSpc(String join_yn, Member memberId, RecruitBoard recruitBoard){
        this.join_yn = join_yn;
        this.memberId = memberId;
        this.recruitBoard = recruitBoard;

    }
}
