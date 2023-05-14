package com.example.messageBoard.repository;

import com.example.messageBoard.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    Member findByNick(String nick);

}
