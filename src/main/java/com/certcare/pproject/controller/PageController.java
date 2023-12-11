package com.certcare.pproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
    // 페이지 요청
    // 등록, 수정, 삭제는 이 클래스에서 다루지 않음

    @GetMapping("/")
    public String showHome() {
        return "home";
    }
    @GetMapping("/main")
    public String showMainPage(Model model) {

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

    @GetMapping("/mypage")
    public String showMyPage() {
        return "mypage";
    }



    // 게시물 상세 조회
    @GetMapping("/certtype")
    public String showCerttypePage() {
        return "certtype";
    }

    @GetMapping("/certdetail")
    public String showCertdetailPage() {
        return "certdetail";
    }

    @GetMapping("/chatbot")
    public String showChatbotPage() {
        return "chatbot";
    }

}

