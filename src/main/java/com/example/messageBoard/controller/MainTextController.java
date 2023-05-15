package com.example.messageBoard.controller;

import com.example.messageBoard.dto.MainTextFormDto;
import com.example.messageBoard.dto.MessageDto;
import com.example.messageBoard.service.MainTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainTextController {
    private final MainTextService mainTextService;

    @GetMapping(value = "/admin/mainText")
    public String mainTextDtl(Model model){
        MainTextFormDto mainTextFormDto = mainTextService.findMainTextDto();
        model.addAttribute("mainTextFormDto", mainTextFormDto);
        return "board/boardForm";
    }

    @PostMapping(value = "/admin/mainText")
    public String mainTextUpdate(@Valid MainTextFormDto mainTextFormDto, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "board/boardForm";
        }
        try {
            mainTextService.updateMainText(mainTextFormDto, principal);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "게시판 제목 수정 중 에러가 발생하였습니다.");
            return "board/boarForm";
        }
        return "redirect:/"; // 다시 실행 /
    }
}
