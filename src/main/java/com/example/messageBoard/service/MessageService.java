package com.example.messageBoard.service;

import com.example.messageBoard.dto.MessageFormDto;
import com.example.messageBoard.entity.Message;
import com.example.messageBoard.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Long saveMessage(MessageFormDto messageFormDto){
        Message message = messageFormDto.createMessage();
        messageRepository.save(message);
        return message.getId();
    }
}
