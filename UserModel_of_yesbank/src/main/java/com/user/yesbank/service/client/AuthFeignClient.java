package com.user.yesbank.service.client;

import com.user.yesbank.dto.RegisterRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authuser")
public interface AuthFeignClient {

    @PostMapping("/api/auth/register")
    ResponseEntity<String> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO);
}

