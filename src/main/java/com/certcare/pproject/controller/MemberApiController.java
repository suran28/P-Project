package com.certcare.pproject.controller;

import com.certcare.pproject.dto.TokenDto;
import com.certcare.pproject.dto.MemberRequestDto;
import com.certcare.pproject.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Slf4j
public class MemberApiController {
    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.signup(memberRequestDto);
        return ResponseEntity.ok("회원 가입이 완료되었습니다");
    }

//    @PostMapping("/signup")
//    public String signup(@RequestParam String username,
//                         @RequestParam String userId,
//                         @RequestParam String password) {
//        MemberRequestDto memberRequestDto = new MemberRequestDto();
//        memberRequestDto.setUsername(username);
//        memberRequestDto.setUserId(userId);
//        memberRequestDto.setPassword(password);
//
//        memberService.signup(memberRequestDto);
//        return "redirect:/login";
//    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        TokenDto tokenDto = memberService.login(memberRequestDto);
        return ResponseEntity.ok(tokenDto);
    }

    // 로그아웃

}
