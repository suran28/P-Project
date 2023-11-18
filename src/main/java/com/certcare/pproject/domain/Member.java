package com.certcare.pproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String memberId;
    private String password;
    @Enumerated(EnumType.STRING)
    private Authority authority;
    private String email;
    // 관심 대직무분야
    @OneToMany(mappedBy = "member")
    private List<Article> articleList;
}
