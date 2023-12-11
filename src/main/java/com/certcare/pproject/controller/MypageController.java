package com.certcare.pproject.controller;

import com.certcare.pproject.dto.MyCertInfoDto;
import com.certcare.pproject.dto.MemberResponseDto;
import com.certcare.pproject.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class MypageController {
    private final MemberService memberService;
    // 나의 보유자격증 개수..

    // 나의 정보 조회
    @GetMapping("/mypage/user-info")
    public ModelAndView showUserInfo(Authentication authentication,
                                     Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long memberId = Long.valueOf(userDetails.getUsername());

        MemberResponseDto memberResponseDto = memberService.showMemberInfo(memberId);
        model.addAttribute("userInfo", memberResponseDto);
        return new ModelAndView("mypage", "model", model);
    }

    // 나의 자격증 조회
    @GetMapping("/mypage/cert")
    public ResponseEntity<List<MyCertInfoDto>> showMyCert(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<MyCertInfoDto> dtos = memberService.showMyCertInfo(Long.valueOf(userDetails.getUsername()));
        return ResponseEntity.ok(dtos);
    }

    // 나의 자격증 등록
    @PostMapping("/mypage/cert/new")
    public String createMyCertRequest(@RequestParam String certName,
                                      @RequestParam String host,
                                      @RequestParam String acqDate,
                                      Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long memberId = Long.valueOf(userDetails.getUsername());

        memberService.createMyCert(certName, host, LocalDateTime.parse(acqDate), memberId);
        return "";
    }

    // 나의 자격증 수정
    @PatchMapping("/mypage/cert/{mycert_id}")
    public String updateMyCertRequest(@PathVariable String mycert_id,
                                      @RequestParam String certName,
                                      @RequestParam String host,
                                      @RequestParam String acqDate) {
        memberService.updateMyCert(Long.valueOf(mycert_id), certName, host, LocalDateTime.parse(acqDate));

        return "";
    }

    // 나의 자격증 삭제
    @DeleteMapping("/mypage/cert/{mycert_id}")
    public String deleteMyCertRequest(@PathVariable String mycert_id) {
        memberService.deleteMyCert(Long.valueOf(mycert_id));
        return "";
    }
}
