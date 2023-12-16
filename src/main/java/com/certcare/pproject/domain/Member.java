package com.certcare.pproject.domain;

import com.certcare.pproject.dto.MemberRequestDto;
import com.certcare.pproject.dto.MemberResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(unique = true)
    private String userId;
    private String password;
    @Enumerated(EnumType.STRING)
    private Authority authority;

    // 관심 대직무분야
//    @OneToMany(mappedBy = "member")
//    private List<Article> articleList;

    public Member(MemberRequestDto dto, PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        this.username = dto.getUsername();
        this.userId = dto.getUserId();
        this.password = encodedPassword;


        // Authority는 자동으로 ROLE_USER
        this.authority = Authority.ROLE_USER;
    }

    public MemberResponseDto toMemberResponseDtoForAdmin() {
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setId(this.id);
        memberResponseDto.setUserId(this.userId);
        memberResponseDto.setUsername(this.username);
        return memberResponseDto;
    }
}

