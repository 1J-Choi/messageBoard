package com.example.messageBoard.service;

import com.example.messageBoard.constant.Role;
import com.example.messageBoard.dto.MainTextFormDto;
import com.example.messageBoard.entity.MainText;
import com.example.messageBoard.entity.Member;
import com.example.messageBoard.repository.MainTextRepository;
import com.example.messageBoard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.jboss.jandex.Main;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MainTextService {
    private final MainTextRepository mainTextRepository;
    private final MemberRepository memberRepository;

    public MainTextFormDto findMainTextDto(){
        MainText mainText = mainTextRepository.findJustOne();

        if(mainText == null){
            mainText = MainText.createFirstMainText();
            mainTextRepository.save(mainText);
        }

        MainTextFormDto mainTextFormDto = MainTextFormDto.builder()
                .id(mainText.getId())
                .title(mainText.getTitle())
                .subText(mainText.getSubText())
                .build();

        return mainTextFormDto;
    }

    public Long updateMainText(MainTextFormDto mainTextFormDto, Principal principal) throws Exception {
        checkMemberRole(principal.getName());

        MainText mainText = mainTextRepository.findById(mainTextFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        mainText.updateMainText(mainTextFormDto);

        return mainText.getId();
    }

    private void checkMemberRole(String email){
        Member findMember = memberRepository.findByEmail(email);
        if (findMember.getRole() != Role.ADMIN){
            throw new IllegalStateException("수정 권한이 없습니다.");
        }
    }
}
