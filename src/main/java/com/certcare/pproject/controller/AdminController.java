package com.certcare.pproject.controller;

import com.certcare.pproject.dto.MemberResponseDto;
import com.certcare.pproject.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;
    // 관리자 로그인, 로그아웃

    // 회원 목록 조회
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> requestAllMembers() {

        List<MemberResponseDto> dtos = adminService.getAllMembers();
        return ResponseEntity.ok(dtos);
    }
}
