package com.certcare.pproject.dto;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Message {
    @JsonProperty("role")
    private String role;
    @JsonProperty("content")
    private String content;
}
