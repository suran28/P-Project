package com.certcare.pproject.controller;

import com.certcare.pproject.dto.TokenDto;
import com.certcare.pproject.dto.MemberRequestDto;
import com.certcare.pproject.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    // 회원가입 api (삭제 예정)
//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody MemberRequestDto memberRequestDto) {
//        memberService.signup(memberRequestDto);
//        return ResponseEntity.ok("회원 가입이 완료되었습니다");
//    }

    // 회원가입
    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String userId,
                         @RequestParam String password) {
        MemberRequestDto memberRequestDto = new MemberRequestDto();
        memberRequestDto.setUsername(username);
        memberRequestDto.setUserId(userId);
        memberRequestDto.setPassword(password);

        memberService.signup(memberRequestDto);
        return "redirect:/login";
    }

    // 로그인 api (삭제 예정)
//    @PostMapping("/login")
//    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
//        TokenDto tokenDto = memberService.login(memberRequestDto);
//        return ResponseEntity.ok(tokenDto);
//    }
    // 로그인
    @PostMapping("/login")
    public String login(@RequestParam String userId,
                        @RequestParam String password,
                        Model model) {
        // 서비스에 넘겨줄 memberRequestDto 객체 생성 및 초기화
//        MemberRequestDto memberRequestDto = new MemberRequestDto();
//        memberRequestDto.setUserId(userId);
//        memberRequestDto.setPassword(password);

        // 로그인 로직
        TokenDto tokenDto = memberService.login(userId, password);

        // 반환할 모델 세팅
        model.addAttribute("grantType", tokenDto.getGrantType());
        model.addAttribute("accessToken", tokenDto.getAccessToken());

        return"redirect:/main";
    }

    // 로그아웃

}
