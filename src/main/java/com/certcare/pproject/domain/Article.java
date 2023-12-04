package com.certcare.pproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;

    // REG DATE
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name= "memberId")
//    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "boardId")
    private Board board;
}
