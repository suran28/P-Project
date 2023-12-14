package com.certcare.pproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChatBadResponse {
    private String question;
    private String answer;
    private String regDate;
    private String username;
}
