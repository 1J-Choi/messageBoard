package com.example.messageBoard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MainTextFormDto {

    private Long id;
    private String title;
    private String subText;
}
