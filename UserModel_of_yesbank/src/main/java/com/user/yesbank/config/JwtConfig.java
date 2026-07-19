//package com.user.yesbank.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//
//@Configuration
//@EnableMethodSecurity
//public class JwtConfig {
//    @Bean
//    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
//
//        JwtGrantedAuthoritiesConverter authoritiesConverter =
//                new JwtGrantedAuthoritiesConverter();
//
//        // Keycloak roles
//        authoritiesConverter.setAuthorityPrefix("ROLE_");
//
//        // Read roles from realm_access.roles
//        authoritiesConverter.setAuthoritiesClaimName("realm_access.roles");
//
//        JwtAuthenticationConverter jwtConverter =
//                new JwtAuthenticationConverter();
//
//        jwtConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
//
//        return jwtConverter;
//    }
//}