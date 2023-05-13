package com.example.messageBoard.repository;

import com.example.messageBoard.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
