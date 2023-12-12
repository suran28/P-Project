package com.certcare.pproject.controller;

import com.certcare.pproject.dto.BannerDto;
import com.certcare.pproject.service.BannerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PageController {
    // 페이지 요청
    // 등록, 수정, 삭제는 이 클래스에서 다루지 않음
    private final BannerService bannerService;

    @GetMapping("/")
    public String showMainPage(Model model) {
        List<BannerDto> dtos = bannerService.getBannerInfo();
        model.addAttribute("bannerInfo", dtos);
        return "main";
    }

    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signup";
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/chatbot")
    public String showChatbotPage() {
        return "chatbot";
    }
}

