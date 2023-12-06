package com.certcare.pproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String showHome() {
        return "home";
    }
    @GetMapping("/main")
    public String showMainPage() {
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

    @GetMapping("/board")
    public String showBoardPage() {
        return "board";
    }
}
