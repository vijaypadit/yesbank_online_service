package com.security.jwt.dto;

import com.security.jwt.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    private Long id;
    private String username;
    private String email;
    private Set<UserRole> roles;


}
