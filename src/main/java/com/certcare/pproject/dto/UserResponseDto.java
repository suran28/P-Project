package com.certcare.pproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String memberId;
    private String password;
    private String name;
    private String email;
}
