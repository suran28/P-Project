package com.certcare.pproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CommentDto {
    private String writer;
    private String body;
    private LocalDateTime regDate;
}
