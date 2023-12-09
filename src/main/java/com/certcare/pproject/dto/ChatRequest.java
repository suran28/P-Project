package com.certcare.pproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ChatRequest {
    private String model;
    private List<Message> message;
    private float top_p;
}
