package com.example.playmaker.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    boolean existsByUserId(String nickname);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    boolean existsByContact(String contact);
}
