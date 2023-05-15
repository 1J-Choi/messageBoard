package com.example.messageBoard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "maintext")
@Getter
@Setter
public class MainText {
    @Id
    @Column(name = "maintext_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String subText;

    public static MainText createFirstMainText(){
        MainText mainText = new MainText();
        mainText.setTitle("게시판에 오신 것을 환영합니다.");
        mainText.setSubText("욕설, 비방, 음란물 게시 금지!");
        return mainText;
    }
}
