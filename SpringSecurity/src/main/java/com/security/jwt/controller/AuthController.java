package com.security.jwt.controller;


import com.security.jwt.dto.AuthDTO;
import com.security.jwt.dto.JwtTokenResponse;
import com.security.jwt.dto.LoginRequest;
import com.security.jwt.enity.AuthUser;
import com.security.jwt.service.AuthUserService;
import jakarta.ws.rs.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUserService authUserService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthUserService authUserService, AuthenticationManager authenticationManager) {
        this.authUserService = authUserService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register-user")
    public AuthDTO registerUser(@RequestBody AuthUser authUser) {
        AuthDTO authDTO = authUserService.saveUser(authUser);
        return authDTO;
    }

    @PostMapping("/generate-token")
    public JwtTokenResponse generateToken(@RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                return authUserService.generateToken(loginRequest.getUsername());
            } else {
                throw new BadRequestException("Invalid Credentials");
            }

        } catch (Exception e) {
            throw new BadRequestException("Invalid Credentials");
        }
    }


}
