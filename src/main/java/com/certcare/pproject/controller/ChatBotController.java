package com.certcare.pproject.controller;

import com.certcare.pproject.service.ChatBotService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChatBotController {
    private final ChatBotService chatBotService;

    @PostMapping("/ask")
    public ResponseEntity<String> askOpenAI(@RequestBody String userRequest) {
        String response = chatBotService.askChatBot(userRequest);
        return ResponseEntity.ok(response);
    }
}
