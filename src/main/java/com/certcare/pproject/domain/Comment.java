package com.certcare.pproject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    @PrePersist
    public void prePersist() {
        if (this.regDate == null) {
            this.regDate = LocalDateTime.now();
        }
    }
    private LocalDateTime regDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "articleId")
    private Article article;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "memberId")
    private Member member;

    public Comment(Member member, Article article, String body) {
        this.member = member;
        this.article = article;
        this.body = body;
    }
}
