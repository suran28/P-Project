package com.certcare.pproject.service;

import com.certcare.pproject.domain.ChatBot;
import com.certcare.pproject.domain.Member;
import com.certcare.pproject.dto.ChatBadResponse;
import com.certcare.pproject.dto.MemberResponseDto;
import com.certcare.pproject.repository.ChatBotRepository;
import com.certcare.pproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminService {
    private final MemberRepository memberRepository;
    private final ChatBotRepository chatBotRepository;

    // 모든 회원 조회
    public List<MemberResponseDto> getAllMembers() {

        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> dtos = members.stream()
                .map(member -> member.toMemberResponseDtoForAdmin())
                .collect(Collectors.toList());

        return dtos;
    }

    // 모든 미흡한 챗봇 응답 조회
    public List<ChatBadResponse> getAllBadChats() {
        List<ChatBot> chatBots = chatBotRepository.findAllByOrderByIdDesc();

        List<ChatBadResponse> dtos = chatBots.stream()
                .map(chatBot -> chatBot.toChatBadResponse())
                .collect(Collectors.toList());

        return dtos;
    }

    // 게시글과 게시글 별 댓글 전체 조회
//    public
    // 게시글 삭제

    // 댓글 삭제
}
