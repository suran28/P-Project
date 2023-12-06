package com.certcare.pproject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Value("${openai.apiKey}")
    private String apiKey;
}
