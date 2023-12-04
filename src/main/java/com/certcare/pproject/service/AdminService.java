package com.certcare.pproject.service;

import com.certcare.pproject.domain.Member;
import com.certcare.pproject.dto.MemberResponseDto;
import com.certcare.pproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {
    private final MemberRepository memberRepository;

    public List<MemberResponseDto> getAllMembers() {
        List<MemberResponseDto> dtos = new ArrayList<>();

        List<Member> members = memberRepository.findAll();

        return dtos;
    }
}
