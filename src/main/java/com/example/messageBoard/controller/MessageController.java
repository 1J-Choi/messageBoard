package com.example.messageBoard.controller;

import com.example.messageBoard.dto.MessageFormDto;
import com.example.messageBoard.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping(value = "/message/new")
    public String messageForm(Model model){
        model.addAttribute("messageFormDto", new MessageFormDto());
        return "message/messageForm";
    }

    @PostMapping(value = "/message/new")
    public String messageNew(@Valid MessageFormDto messageFormDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "message/messageForm";
        }

        try{
            messageService.saveMessage(messageFormDto);
        }catch (Exception e){
            model.addAttribute("errorMessage", "게시글 등록 중 에러가 발생했습니다.");
            return "message/messageForm";
        }
        return "redirect:/";
    }
}
