package com.yesbank.getwayserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain security(ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/yesbank/authuser/**",
                                "/yesbank/users/**",
                                "/yesbank/cards/**",
                                "/yesbank/banks/**",
                                "/yesbank/bankaccounts/**")
                        .permitAll()
                        .anyExchange().authenticated())
                .build();
    }
}
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Bean
//    SecurityWebFilterChain security(ServerHttpSecurity http) {
//
//        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
//
//                .authorizeExchange(exchange -> exchange
//
//                        .pathMatchers("/yesbank/authuser/**").permitAll()
//
//                        .anyExchange().authenticated())
//
//                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
//
//                .formLogin(ServerHttpSecurity.FormLoginSpec::disable);
//
//        return http.build();
//    }
//}