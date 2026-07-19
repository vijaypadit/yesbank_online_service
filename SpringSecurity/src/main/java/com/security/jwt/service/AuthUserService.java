package com.security.jwt.service;


import com.security.jwt.dto.AuthDTO;
import com.security.jwt.dto.JwtTokenResponse;
import com.security.jwt.enity.AuthUser;
import com.security.jwt.repository.AuthUserRepository;
import com.security.jwt.utill.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthUserService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthDTO saveUser(AuthUser authUser) {
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setCreatedAt(LocalDateTime.now());
        AuthUser savedUser = authUserRepository.save(authUser);
        return new AuthDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole());
    }

    public JwtTokenResponse generateToken(String username) {
        String token = jwtUtil.generateToken(username);
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
        jwtTokenResponse.setToken(token);
        jwtTokenResponse.setType("Bearer");
        jwtTokenResponse.setValidUntil(jwtUtil.extractExpiration(token).toString());
        return jwtTokenResponse;
    }
}
