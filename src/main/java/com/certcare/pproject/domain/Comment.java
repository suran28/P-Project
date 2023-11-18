package com.certcare.pproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "memberId")
    private Article article;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "memberId")
    private Member member;
}
