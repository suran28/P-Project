package com.certcare.pproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    // 모든 데이터 조회 요청 및 페이지 요청
    // 등록, 수정, 삭제는 이 클래스에서 다루지 않음

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

    // 카테고리 별 게시판 목록 전체 조회 (수정 필요)
    @GetMapping("/board/1")
    public String showArticleList() {
        return "board";
    }

    // 게시물 상세 조회

    @GetMapping("/community")
    public String showCommunityPage() {
        return "community";
    }

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

