package com.example.rolling_paper.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginResponse {
    private boolean success;

    private String token;

    private String message;
}