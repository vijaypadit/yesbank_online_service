package com.security.jwt.dto;

import lombok.Data;

@Data
public class JwtTokenResponse {

    private String token;
    private String type;
    private String validUntil;
}
