package com.certcare.pproject.controller;

import com.certcare.pproject.dto.MemberRequestDto;
import com.certcare.pproject.dto.TokenDto;
import com.certcare.pproject.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Controller
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원가입 페이지 반환
    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signup";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
//    @PostMapping("/signup")
//    public String signup(@RequestBody MemberRequestDto memberRequestDto) {
//        memberService.signup(memberRequestDto);
//        return "home";
//    }

    // 로그인
//    @PostMapping("/login")
//    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
//        TokenDto tokenDto = memberService.login(memberRequestDto);
//        return ResponseEntity.ok(tokenDto);
//    }
}
