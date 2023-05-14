package com.example.messageBoard.repository;
import com.example.messageBoard.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByMember_Id(Long memberId);
}
