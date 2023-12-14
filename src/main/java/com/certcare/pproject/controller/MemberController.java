package com.certcare.pproject.controller;

import com.certcare.pproject.dto.TokenDto;
import com.certcare.pproject.dto.MemberRequestDto;
import com.certcare.pproject.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@AllArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    // 회원가입 api (삭제 예정)
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.signup(memberRequestDto);
        return ResponseEntity.ok("회원 가입이 완료되었습니다");
    }

    // 로그인 api (삭제 예정)
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        TokenDto tokenDto = memberService.login(memberRequestDto);
        return ResponseEntity.ok(tokenDto);
    }


    // 로그아웃

}
