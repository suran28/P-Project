package com.certcare.pproject.controller;

import com.certcare.pproject.dto.MyCertInfoDto;
import com.certcare.pproject.dto.MemberResponseDto;
import com.certcare.pproject.jwt.TokenProvider;
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
    private final TokenProvider tokenProvider;

    @GetMapping("/mypage")
    public String showMyPage(Model model,
                             @CookieValue(name = "accessToken", defaultValue = "default") String accessToken) {
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long memberId = Long.valueOf(userDetails.getUsername());

        // 나의 정보 (닉네임, 아이디)
        MemberResponseDto memberResponseDto = memberService.showMemberInfo(memberId);
        model.addAttribute("userInfo", memberResponseDto);

        // 나의 보유 자격증
        List<MyCertInfoDto> dtos = memberService.showMyCertInfo(memberId);
        model.addAttribute("myCerts", dtos);

        // 나의 보유자격증 개수..
        int myCertNum = dtos.size();
        model.addAttribute("myCertNum", myCertNum);

        return "mypage";
    }

    // 나의 자격증 조회 (삭제 예정)
//    @GetMapping("/mypage/cert")
//    public ResponseEntity<List<MyCertInfoDto>> showMyCert(Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        List<MyCertInfoDto> dtos = memberService.showMyCertInfo(Long.valueOf(userDetails.getUsername()));
//        return ResponseEntity.ok(dtos);
//    }

    // 나의 자격증 등록
    @PostMapping("/mypage/cert/new")
    @ResponseBody
    public ResponseEntity<String> createMyCertRequest(@RequestBody MyCertInfoDto dto,
                                      @CookieValue(name = "accessToken", defaultValue = "default") String accessToken) {
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long memberId = Long.valueOf(userDetails.getUsername());

        memberService.createMyCert(dto.getCert_name(), dto.getHost(), dto.getAcq_date(), memberId);
        return ResponseEntity.ok("나의 자격증 등록 성공");
    }

    // 나의 자격증 수정
    @PatchMapping("/mypage/cert/{mycert_id}")
    @ResponseBody
    public ResponseEntity<String> updateMyCertRequest(@PathVariable String mycert_id,
                                                      @RequestBody MyCertInfoDto dto) {
        memberService.updateMyCert(Long.valueOf(mycert_id),dto.getCert_name(), dto.getHost(), dto.getAcq_date());

        return ResponseEntity.ok("나의 자격증 수정 성공");
    }

    // 나의 자격증 삭제
    @DeleteMapping("/mypage/cert/{mycert_id}")
    @ResponseBody
    public ResponseEntity<String> deleteMyCertRequest(@PathVariable String mycert_id) {
        memberService.deleteMyCert(Long.valueOf(mycert_id));
        return ResponseEntity.ok("나의 자격증 삭제 성공");
    }
}
