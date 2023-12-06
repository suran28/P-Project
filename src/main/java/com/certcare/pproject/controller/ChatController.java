package com.certcare.pproject.controller;

import com.certcare.pproject.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class ChatController {
//    private final ChatService chatService;
//    @PostMapping("/ask")
//    public ResponseEntity<String> askOpenAI(@RequestBody String userRequest) {
//        return chatService.askOpenAI(userRequest);
//    }
//}
