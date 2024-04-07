package com.example.playmaker.domain.teamboard;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamBoardRepository  extends JpaRepository<TeamBoard,Long> {

}
