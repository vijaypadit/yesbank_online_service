package com.yesbank.getwayserver.security;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.function.Predicate;

@Component
public class Validator {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public static  final List<String> endpoints = List.of(
            "/yesbank/authuser/register-user",
            "/yesbank/authuser/generate-token",
            "/yesbank/authuser/validate-token/**"
    );

    public Predicate<ServerHttpRequest> predicate = serverHttpRequest -> {
        String requestPath = serverHttpRequest.getURI().getPath();
        return endpoints.stream()
                .noneMatch(uri -> antPathMatcher.match(uri, requestPath));
    };
}
