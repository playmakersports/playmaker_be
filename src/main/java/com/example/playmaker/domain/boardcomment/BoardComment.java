package com.example.playmaker.domain.boardcomment;


import com.example.playmaker.TimeEntity;
import com.example.playmaker.domain.teamboard.TeamBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class BoardComment extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private String userName;            //mapping 여부 확인
    private String makeDt;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private TeamBoard board;

    @Builder
    public BoardComment(String userName, String makeDt, String content, TeamBoard board)
    {
        this.userName = userName;
        this.makeDt = makeDt;
        this.content = content;
        this.board = board;
    }

}
