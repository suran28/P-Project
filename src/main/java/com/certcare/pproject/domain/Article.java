package com.certcare.pproject.domain;

import com.certcare.pproject.dto.ArticleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    @PrePersist
    public void prePersist() {
        if (this.regDate == null) {
            this.regDate = LocalDateTime.now();
        }
    }
    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "memberId")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "boardId")
    private Board board;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Article(String title, String body, Member member, Board board) {
        this.title = title;
        this.body = body;
        this.member = member;
        this.board = board;
    }

    public void update(String title, String body){
        if (title != null) {
            this.title = title;
        }
        if (body != null) {
            this.body = body;
        }
    }

//    public ArticleDto toArticleDto() {
//    }
}
