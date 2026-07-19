package com.user.yesbank.dto;

import com.user.yesbank.enums.UserRole;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    private Long userId;

    private Long bankId;

    private String username;

    private String password;

    private String email;

    private UserRole role;
}