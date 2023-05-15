package com.example.messageBoard.service;

import com.example.messageBoard.dto.MainTextFormDto;
import com.example.messageBoard.entity.MainText;
import com.example.messageBoard.repository.MainTextRepository;
import lombok.RequiredArgsConstructor;
import org.jboss.jandex.Main;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;

@Service
@Transactional
@RequiredArgsConstructor
public class MainTextService {
    private final MainTextRepository mainTextRepository;

    public MainTextFormDto findMainTextDto(){
        MainText mainText = mainTextRepository.findJustOne();

        if(mainText == null){
            mainText = MainText.createFirstMainText();
            mainTextRepository.save(mainText);
        }

        MainTextFormDto mainTextFormDto = MainTextFormDto.builder()
                .title(mainText.getTitle())
                .subText(mainText.getSubText())
                .build();

        return mainTextFormDto;
    }
}
