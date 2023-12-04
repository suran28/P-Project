package com.certcare.pproject.service;

import com.certcare.pproject.domain.Member;
import com.certcare.pproject.dto.CertInfoDto;
import com.certcare.pproject.dto.MemberRequestDto;
import com.certcare.pproject.dto.MemberResponseDto;
import com.certcare.pproject.dto.TokenDto;
import com.certcare.pproject.jwt.TokenProvider;
import com.certcare.pproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// 인증 관련 서비스 (회원가입, 로그인 ..)
// 마이페이지 서비스

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;


    // 회원가입
    @Transactional
    public void signup(MemberRequestDto memberRequestDto) {
        // 회원가입 시 memberId로 중복회원 검사
        if (memberRepository.existsByUsername(memberRequestDto.getUsername())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
        Member newMember = new Member(memberRequestDto, passwordEncoder);
        memberRepository.save(newMember);
    }

    // 로그인
    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto) {
        Optional<Member> member = memberRepository.findByUsername(memberRequestDto.getUsername());
        if (member.isPresent()) {
            // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
            UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

            // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
            //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

            // 4. RefreshToken 저장
//            RefreshToken refreshToken = RefreshToken.builder()
//                    .key(authentication.getName())
//                    .value(tokenDto.getRefreshToken())
//                    .build();
//
//            refreshTokenRepository.save(refreshToken);

            // 5. 토큰 발급
            return tokenDto;

        } else {
            throw new RuntimeException("유저 정보를 찾을 수 없습니다.");
        }
    }

    // 로그아웃

    // 회원 정보 조회
    public MemberResponseDto showMemberInfo(Long id){
        MemberResponseDto dto = new MemberResponseDto();

        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            // Entity -> dto
        }
        return dto;


    }

    // 회원 정보 수정

    // 회원 정보 삭제

    // 마이페이지 나의 자격증 조회
    public CertInfoDto showMyCertInfo(Long id) {
        CertInfoDto certInfoDto = new CertInfoDto();

        // MyCert Entity 조회
        return certInfoDto;
    }
}
