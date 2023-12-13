package com.certcare.pproject.domain;

import com.certcare.pproject.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Slf4j
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

    public CommentDto toCommentDto(Boolean writerChk) {
        CommentDto commentDto = new CommentDto();

        commentDto.setBody(this.body);
        log.info(this.member.getUsername());
        commentDto.setWriter(this.member.getUsername());
        commentDto.setWriterChk(writerChk);

        // 등록일자 저장 형식: 2023-12-10T00:00:00
        // 날짜 데이터만 보내기
        int indexOfT = this.regDate.toString().indexOf('T');
        if (indexOfT != -1) { // "T"를 찾은 경우
            commentDto.setRegDate(this.regDate.toString().substring(0, indexOfT));
        } else { // "T"를 찾지 못한 경우, 전체 문자열 반환 또는 예외 처리 등을 수행할 수 있음
            commentDto.setRegDate(this.regDate.toString());
        }

        return commentDto;
    }
}
