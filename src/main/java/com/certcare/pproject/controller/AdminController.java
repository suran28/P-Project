package com.certcare.pproject.controller;

import com.certcare.pproject.domain.Member;
import com.certcare.pproject.dto.ChatBadResponse;
import com.certcare.pproject.dto.MemberRequestDto;
import com.certcare.pproject.dto.MemberResponseDto;
import com.certcare.pproject.dto.TokenDto;
import com.certcare.pproject.service.AdminService;
import com.certcare.pproject.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final MemberService memberService;

    // 관리자 로그인
    @GetMapping("/admin/login")
    public String adminLogin(@RequestBody MemberRequestDto memberRequestDto, Model model) {
        TokenDto tokenDto = memberService.login(memberRequestDto);
        model.addAttribute("accessToken", tokenDto.getAccessToken());
        return "admin";
    }

    // 회원 목록 조회
    @GetMapping("/admin/members")
    @ResponseBody
    public ResponseEntity<List<MemberResponseDto>> requestAllMembers() {
        List<MemberResponseDto> dtos = adminService.getAllMembers();
        return ResponseEntity.ok(dtos);
    }
    // 회원 삭제

    // 미흡한 응답 채팅 모니터링
    @GetMapping("/admin/chat-monitoring")
    @ResponseBody
    public ResponseEntity<List<ChatBadResponse>> showAllBadResponse() {
        List<ChatBadResponse> dtos = adminService.getAllBadChats();
        return ResponseEntity.ok(dtos);
    }

    // 게시글 및 댓글 조회

    // 게시글 삭제

    // 댓글 삭제
}
