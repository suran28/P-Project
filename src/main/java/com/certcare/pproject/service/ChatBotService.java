package com.certcare.pproject.service;

import com.certcare.pproject.dto.ChatRequest;
import com.certcare.pproject.dto.Message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChatBotService {
    private final WebClient webClient;
    @Value("${openai.apiKey}")
    private String apiKey;

    public ChatBotService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/engines/davinci-codex/completions")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    // https://platform.openai.com/docs/api-reference/chat/create
    // 위 사이트 가이드라인대로 요청 보내기
    public String askChatBot(String userRequest) {


        ChatRequest chatRequest = new ChatRequest();

        // 수정 필요
        chatRequest.setModel("ft:gpt-3.5-turbo-0613:personal::8E1So7jh");
        chatRequest.setTop_p(0.5F);

        // message 추가
        List<Message> message = new ArrayList<>();
        message.add(createSystemMessage());
        message.add(createUserMessage(userRequest));

        // url 확인하기
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        // Header 설정 필요
        // POST 요청 보내기
        String response = webClient.post()
                .uri(apiUrl)
                .header("Content-Type", "application/json")
                .body((BodyInserter<?, ? super ClientHttpRequest>) chatRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

    public Message createUserMessage(String request) {
        Message message = new Message();
        message.setRole("user");
        message.setContent(request);
        return message;
    }
    public Message createSystemMessage() {
        Message message = new Message();
        message.setRole("system");
        message.setContent("당신은 써트케어 서비스의 어쩌고 저쩌고 일 똑바로 해라");
        return message;
    }

}
