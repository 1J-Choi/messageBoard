package com.example.messageBoard.service;


import com.example.messageBoard.dto.MainMessageDto;
import com.example.messageBoard.dto.MessageDto;
import com.example.messageBoard.dto.MessageFormDto;
import com.example.messageBoard.entity.Member;
import com.example.messageBoard.entity.Message;
import com.example.messageBoard.repository.MemberRepository;
import com.example.messageBoard.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    public Long saveMessage(MessageFormDto messageFormDto, String email){
        Member nowMember = memberRepository.findByEmail(email);
        MessageDto messageDto = MessageDto.builder()
                .id(messageFormDto.getId())
                .title(messageFormDto.getTitle())
                .contents(messageFormDto.getContents())
                .member(nowMember)
                .build();

        Message message = messageDto.createMessage();

        messageRepository.save(message);
        return message.getId();
    }

    public List<MainMessageDto> findMainMessageDto(){
        List<Message> all = messageRepository.findAll();
        List<MainMessageDto> mainMessageDtoList = new ArrayList<>();

        for(Message message : all){
            MainMessageDto dto = MainMessageDto.builder()
                    .id(message.getId())
                    .title(message.getTitle())
                    .memberNick(message.getMember().getNick())
                    .build();

            mainMessageDtoList.add(dto);
        }
        return mainMessageDtoList;
    }

    public List<MainMessageDto> findMainMessageDtoById(Long id){
        List<Message> all = messageRepository.findAllByMember_Id(id);
        List<MainMessageDto> mainMessageDtoList = new ArrayList<>();

        for(Message message : all){
            MainMessageDto dto = MainMessageDto.builder()
                    .id(message.getId())
                    .title(message.getTitle())
                    .contents(message.getContents())
                    .memberNick(message.getMember().getNick())
                    .build();

            mainMessageDtoList.add(dto);
        }
        return mainMessageDtoList;
    }

    @Transactional(readOnly = true)
    public MessageDto getMessageDtl(Long messageId){
        Message message = messageRepository.findById(messageId).orElseThrow(EntityExistsException::new);
        MessageDto messageDto = MessageDto.of(message);
        return messageDto;
    }

    @Transactional(readOnly = true)
    public boolean validateMessage(Long messageId, String email){
        Member curMember = memberRepository.findByEmail(email);
        Message message = messageRepository.findById(messageId)
                .orElseThrow(EntityExistsException::new);
        Member savedMember = message.getMember();

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }
        return true;
    }

    public void deleteMessage(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(EntityExistsException::new);
        messageRepository.delete(message);
    }
}
