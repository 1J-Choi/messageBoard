package com.example.messageBoard.controller;


import com.example.messageBoard.dto.MainMessageDto;
import com.example.messageBoard.entity.Message;
import com.example.messageBoard.service.MessageService;
import groovy.transform.builder.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MessageService messageService;
    @GetMapping(value = {"/"})
    public String main(Model model) {

        List<MainMessageDto> messages = messageService.findMainMessageDto();

        model.addAttribute("messages", messages);
        return "main";
    }
}
