package com.example.playmaker.domain.recruitboard;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitBoardRepository extends JpaRepository<RecruitBoard, Long> {
}
