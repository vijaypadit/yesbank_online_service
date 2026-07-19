package com.yesbank.getwayserver.security;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

@Component
public class JwtUtil {

    public static final String SECRET_KEY = "ASHHDFHSOIUEUBDIFBUIEWGFVSDVFIWWEE487536DGKFHGHDSGFHKSDGFUEFUEVCUKEUFUDVCVDHSVHSDVHF";


    private Key getKey() {
        byte[] bytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }


    public void validateToke(String token) {
        Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token);

    }
}

