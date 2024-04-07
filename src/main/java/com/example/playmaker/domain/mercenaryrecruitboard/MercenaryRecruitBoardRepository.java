package com.example.playmaker.domain.mercenaryrecruitboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercenaryRecruitBoardRepository extends JpaRepository<MercenaryRecruitBoard,Long> {
}
