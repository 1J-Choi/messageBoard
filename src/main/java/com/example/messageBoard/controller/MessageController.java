package com.example.messageBoard.controller;

import com.example.messageBoard.dto.MainMessageDto;
import com.example.messageBoard.dto.MessageDto;
import com.example.messageBoard.dto.MessageFormDto;
import com.example.messageBoard.entity.Member;
import com.example.messageBoard.repository.MemberRepository;
import com.example.messageBoard.service.MemberService;
import com.example.messageBoard.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final MemberService memberService;

    @GetMapping(value = "/message/new")
    public String messageForm(Model model){
        model.addAttribute("messageFormDto", new MessageFormDto());
        return "message/messageForm";
    }

    @PostMapping(value = "/message/new")
    public String messageNew(@Valid MessageFormDto messageFormDto, BindingResult bindingResult, Model model, Principal principal){
        if (bindingResult.hasErrors()){
            return "message/messageForm";
        }

        String email = principal.getName();

        try{
            messageService.saveMessage(messageFormDto, email);
        }catch (Exception e){
            model.addAttribute("errorMessage", "게시글 등록 중 에러가 발생했습니다.");
            return "message/messageForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/message/{messageId}")
    public String messageDtl(Model model, @PathVariable("messageId")Long messageId){
        MessageDto messageDto = messageService.getMessageDtl(messageId);
        String memberNick = messageDto.getMember().getNick();
        model.addAttribute("message", messageDto);
        model.addAttribute("memberNick", memberNick);
        return "message/messageDtl";
    }

    @GetMapping(value = "/myHist")
    public String myMessageMng(Model model, Principal principal){
        Long nowMemberId = memberService.findMemberId(principal.getName());
        List<MainMessageDto> mainMessageDtoList = messageService.findMainMessageDtoById(nowMemberId);
        model.addAttribute("mainMessageDtoList", mainMessageDtoList);
        return "message/messageMng";
    }

    @DeleteMapping(value = "/myHist/{messageId}")
    public @ResponseBody ResponseEntity deleteCartItem(@PathVariable("messageId") Long messageId, Principal principal){
        if (!messageService.validateMessage(messageId, principal.getName())) {
            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        messageService.deleteMessage(messageId);
        return new ResponseEntity<Long>(messageId, HttpStatus.OK);
    }
}
