package com.example.messageBoard.dto;

import com.example.messageBoard.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MainMessageDto {

    private Long id;

    private String title;

    private String contents;

    private String memberNick;
}
