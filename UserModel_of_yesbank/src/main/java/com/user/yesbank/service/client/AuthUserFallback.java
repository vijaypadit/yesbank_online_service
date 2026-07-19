package com.user.yesbank.service.client;

import com.user.yesbank.dto.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class AuthUserFallback {
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return null;
    }
}
