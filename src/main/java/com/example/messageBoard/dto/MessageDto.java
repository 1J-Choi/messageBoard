package com.example.messageBoard.dto;

import com.example.messageBoard.entity.Member;
import com.example.messageBoard.entity.Message;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private Long id;

    private String title;

    private String contents;

    private Member member;


    private static ModelMapper modelMapper = new ModelMapper();

    public Message createMessage(){ return modelMapper.map(this, Message.class); }


    public static MessageDto of(Message message){
        return modelMapper.map(message, MessageDto.class);
    }

}
