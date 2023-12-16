package com.certcare.pproject.service;

import com.certcare.pproject.domain.Member;
import com.certcare.pproject.domain.MyCert;
import com.certcare.pproject.dto.MyCertInfoDto;
import com.certcare.pproject.dto.MemberRequestDto;
import com.certcare.pproject.dto.MemberResponseDto;
import com.certcare.pproject.dto.TokenDto;
import com.certcare.pproject.jwt.TokenProvider;
import com.certcare.pproject.repository.MemberRepository;
import com.certcare.pproject.repository.MyCertRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 인증 관련 서비스 (회원가입, 로그인 ..)
// 마이페이지 서비스

@Service
@Slf4j
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MyCertRepository myCertRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;


    // 회원가입
    @Transactional
    public void signup(MemberRequestDto memberRequestDto) {
        // 회원가입 시 memberId로 중복회원 검사
        if (memberRepository.existsByUserId(memberRequestDto.getUserId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
        Member newMember = new Member(memberRequestDto, passwordEncoder);
        memberRepository.save(newMember);
    }

    // 로그인
    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto) {
        Optional<Member> member = memberRepository.findByUserId(memberRequestDto.getUserId());
        if (member.isPresent()) {

            // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberRequestDto.getUserId(), memberRequestDto.getPassword());

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

            dto.setUsername(member.getUsername());
            dto.setUserId(member.getUserId());
        }
        return dto;
    }

    // 회원 정보 삭제

    // 마이페이지 나의 자격증 목록 조회
    public List<MyCertInfoDto> showMyCertInfo(Long id) {
        List<MyCert> myCerts = myCertRepository.findAllByMemberId(id);

        List<MyCertInfoDto> dtos = new ArrayList<>();

        for (MyCert myCert : myCerts) {
            MyCertInfoDto dto = myCert.toCertInfoDto();
            dtos.add(dto);
        }

        return dtos;
    }

    // 마이페이지 나의 자격증 단일 조회
    public MyCertInfoDto showOneMyCert(Long id) {
        Optional<MyCert> optionalMyCert = myCertRepository.findById(id);

        if (optionalMyCert.isPresent()) {
            MyCert myCert = optionalMyCert.get();
            MyCertInfoDto dto = myCert.toCertInfoDto();
            return dto;
        } else {
            throw new RuntimeException("해당 나의 자격증 정보가 존재하지 않습니다.");
        }
    }

    // 마이페이지 나의 자격증 등록
    public void createMyCert(String certName, String host, String acqDate, Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedAcqDate = LocalDate.parse(acqDate, formatter);

            MyCert myCert = new MyCert(certName, host, parsedAcqDate, member);
            myCertRepository.save(myCert);
        }
    }

    // 마이페이지 나의 자격증 수정
    public void updateMyCert(Long myCertId, String certName, String host, String acqDate) {
        Optional<MyCert> optionalMyCert = myCertRepository.findById(myCertId);
        if (optionalMyCert.isPresent()) {
            MyCert updatedMyCert = optionalMyCert.get();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedAcqDate = LocalDate.parse(acqDate, formatter);
            updatedMyCert.update(certName, host, parsedAcqDate);
            myCertRepository.save(updatedMyCert);
        }
    }

    // 마이페이지 나의 자격증 삭제
    public void deleteMyCert(Long myCertId) {
        Optional<MyCert> optionalMyCert = myCertRepository.findById(myCertId);
        if (optionalMyCert.isPresent()) {
            MyCert myCert = optionalMyCert.get();
            myCertRepository.delete(myCert);
        }
    }
}
