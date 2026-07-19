//package com.user.yesbank.config;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.oauth2.jwt.Jwt;
//
//@Configuration
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter;
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//
//                .authorizeHttpRequests(auth -> auth
//
//                        .requestMatchers("/actuator/**").permitAll()
//
//                        .requestMatchers("/yesbank/users/api/create")
//                        .hasRole("ADMIN")
//
//                        .anyRequest()
//                        .authenticated())
//
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter)));
//
//        return http.build();
//    }
//}