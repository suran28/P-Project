package com.certcare.pproject.service;

import com.certcare.pproject.config.OpenAiConfig;
import com.certcare.pproject.domain.ChatBot;
import com.certcare.pproject.domain.Member;
import com.certcare.pproject.dto.ChatBadResponse;
import com.certcare.pproject.repository.ChatBotRepository;
import com.certcare.pproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;


@Service
@AllArgsConstructor
public class ChatBotService {
    @Value("${openai.apiKey}")
    private String apiKey;
    // https://platform.openai.com/docs/api-reference/chat/create
    private final MemberRepository memberRepository;
    private final ChatBotRepository chatBotRepository;
    // 위 사이트 가이드라인대로 요청 보내기
    @Transactional
    public String askChatBot(String userRequest) {

        WebClient webClient = WebClient.create();

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", userRequest);
        messages.add(userMessage);

        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "당신은 존댓말로 공손하게 대답하는 챗봇입니다. 당신은 자격증 정보에 관한 질문에만 정확하고 간결하게 답변합니다. 자격증 이외의 질문에 대해서는 답변할 수 없다고 대답합니다.");
        messages.add(systemMessage);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("messages", messages);
        requestBody.put("model", "ft:gpt-3.5-turbo-0613:personal::8WJGgXOf");
        requestBody.put("temperature", 0.3);
//        requestBody.put("top_p", 0.5);

        String apiUrl = "https://api.openai.com/v1/chat/completions";

        String response = webClient.post()
                .uri(apiUrl)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

    @Transactional
    public void saveBadResponseChat(ChatBadResponse chatBadResponse, Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            ChatBot chatBot = new ChatBot(chatBadResponse, optionalMember.get());
            chatBotRepository.save(chatBot);
        } else {
            throw new RuntimeException("로그인 후 사용할 수 있습니다.");
        }
    }

}
