package com.certcare.pproject.controller;

import com.certcare.pproject.dto.CertInfoDto;
import com.certcare.pproject.dto.MemberResponseDto;
import com.certcare.pproject.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MypageApiController {
    private final MemberService memberService;

//    마이페이지
//    @GetMapping
//    public ResponseEntity<MemberResponseDto> showMyPage(Authentication authentication){
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        MemberResponseDto dto = memberService.showMemberInfo(Long.valueOf(userDetails.getUsername()));
//        return ResponseEntity.ok(dto);
//    }
//// 회원정보 조회
//
//    //    나의 자격증 조회
    @GetMapping("/mypage/cert")
    public ResponseEntity<List<CertInfoDto>> showMyCert(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<CertInfoDto> dtos = memberService.showMyCertInfo(Long.valueOf(userDetails.getUsername()));
        return ResponseEntity.ok(dtos);
    }
//    나의 자격증 등록, 수정, 삭제

// /mypage/cert,get/post/patch/delete
//

}
