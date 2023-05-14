package com.example.messageBoard.dto;

import com.example.messageBoard.entity.Member;
import com.example.messageBoard.entity.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class MessageDto {
    private Long id;

    private String title;

    private String contents;

    private Member member;

    private static ModelMapper modelMapper = new ModelMapper();

    public Message createMessage(){ return modelMapper.map(this, Message.class); }

    public static MessageFormDto of(Message message){
        return modelMapper.map(message, MessageFormDto.class);
    }
}
