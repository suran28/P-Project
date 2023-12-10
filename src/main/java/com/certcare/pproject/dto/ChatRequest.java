package com.certcare.pproject.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ChatRequest {
    @JsonProperty("model")
    private String model;
    @JsonProperty("messages")
    private List<Message> message;
    @JsonProperty("top_p")
    private float top_p;
}
