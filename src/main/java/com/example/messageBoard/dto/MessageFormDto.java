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
public class MessageFormDto {
    private Long id;

    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    @NotBlank(message = "내용을 입력하세요.")
    private String contents;

}