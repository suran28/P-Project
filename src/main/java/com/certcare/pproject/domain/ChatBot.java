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

        int indexOfT = this.regDate.toString().indexOf('T');
        if (indexOfT != -1) { // "T"를 찾은 경우
            chatBadResponse.setRegDate(this.regDate.toString().substring(0, indexOfT));
        } else { // "T"를 찾지 못한 경우, 전체 문자열 반환 또는 예외 처리 등을 수행할 수 있음
            chatBadResponse.setRegDate(this.regDate.toString());
        }
        return chatBadResponse;
    }
}
