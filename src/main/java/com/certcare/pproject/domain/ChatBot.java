package com.certcare.pproject.domain;

import com.certcare.pproject.dto.ChatBadResponse;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class ChatBot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String answer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "memberId")
    private Member member;

    @PrePersist
    public void prePersist() {
        if (this.regDate == null) {
            this.regDate = LocalDateTime.now();
        }
    }
    private LocalDateTime regDate;

    public ChatBot(ChatBadResponse chatBadResponse, Member member) {
        this.question = chatBadResponse.getQuestion();
        this.answer = chatBadResponse.getAnswer();
        this.member = member;
    }

    // Dto로 변환
    public ChatBadResponse toChatBadResponse() {
        ChatBadResponse chatBadResponse = new ChatBadResponse();
        chatBadResponse.setQuestion(this.question);
        chatBadResponse.setAnswer(this.answer);
        chatBadResponse.setUsername(this.member.getUsername());
        chatBadResponse.setRegDate(String.valueOf(this.regDate));
        return chatBadResponse;
    }
}
