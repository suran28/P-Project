package com.certcare.pproject.controller;

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

    @GetMapping("/admin")
    public String showAdminLoginPage() {
        return "admin";
    }

    // 관리자 로그인
    @PostMapping("/admin/login")
    @ResponseBody
    public ResponseEntity<TokenDto> adminLogin(@RequestBody MemberRequestDto memberRequestDto) {
        TokenDto tokenDto = memberService.login(memberRequestDto);
        return ResponseEntity.ok(tokenDto);
    }

    // 회원 목록 조회
    @GetMapping("/admin/members")
    public String requestAllMembers(Model model) {
        List<MemberResponseDto> dtos = adminService.getAllMembers();
        model.addAttribute("members", dtos);
        return "members";
    }

    // 미흡한 응답 채팅 모니터링
    @GetMapping("/admin/chat-monitoring")
    public String showAllBadResponse(Model model) {
        List<ChatBadResponse> dtos = adminService.getAllBadChats();
        model.addAttribute("chats", dtos);
        return "chatmonitor";
    }
}
