package com.certcare.pproject.dto;

import com.certcare.pproject.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ArticleDto {
    private Long id;
    private String title;
    private String writer;
    private LocalDateTime regDate;
    private String url;
    private String body;
}
