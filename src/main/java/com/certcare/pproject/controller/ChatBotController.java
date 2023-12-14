package com.certcare.pproject.controller;

import com.certcare.pproject.dto.ChatBadResponse;
import com.certcare.pproject.jwt.TokenProvider;
import com.certcare.pproject.service.ChatBotService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChatBotController {
    private final ChatBotService chatBotService;
    private final TokenProvider tokenProvider;

    @PostMapping("/ask")
    public ResponseEntity<String> askOpenAI(@RequestBody String userRequest) {
        String response = chatBotService.askChatBot(userRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/bad-answer")
    public ResponseEntity<String> badAnswer(@RequestBody ChatBadResponse chatBadResponse,
                                            @CookieValue(name = "accessToken", defaultValue = "default") String accessToken) {
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long memeberId = Long.valueOf(userDetails.getUsername());
        chatBotService.saveBadResponseChat(chatBadResponse,memeberId);

        return ResponseEntity.ok("반영되었습니다.");
    }
}
