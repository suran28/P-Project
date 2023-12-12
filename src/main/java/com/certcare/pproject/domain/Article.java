package com.certcare.pproject.domain;

import com.certcare.pproject.dto.ArticleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Slf4j
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


    // detail이 true면 상세 게시물 조회로, dto에 게시물 본문까지 담아서 전송
    // false면 목록 조회로 게시물 본문은 담지 않음
    public ArticleDto toArticleDto(Boolean detail, Boolean writerChk) {
        ArticleDto articleDto = new ArticleDto();

        articleDto.setId(this.id);
        articleDto.setWriter(this.member.getUsername());
        articleDto.setTitle(this.title);

        // 등록일자 저장 형식: 2023-12-10T00:00:00
        // 날짜 데이터만 보내기
        int indexOfT = this.regDate.toString().indexOf('T');
        if (indexOfT != -1) { // "T"를 찾은 경우
            articleDto.setRegDate(this.regDate.toString().substring(0, indexOfT));
        } else { // "T"를 찾지 못한 경우, 전체 문자열 반환 또는 예외 처리 등을 수행할 수 있음
            articleDto.setRegDate(this.regDate.toString());
        }

        articleDto.setUrl("/board/"+this.board.getId().toString()+"/article/"+this.id);
        articleDto.setWriterChk(writerChk);

        if (detail == true) {
            articleDto.setBody(this.body);
        }

        return articleDto;
    }
}
