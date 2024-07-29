package com.vashchenko.micro.edu.apigateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class OutcomeJwtProvider {

    private final Key publicKey;

    public OutcomeJwtProvider(@Value("${jwt.outcome.secret}") String publicKey) {
        byte[] keyBytes = Decoders.BASE64.decode(publicKey);
        this.publicKey= Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(Claims claims) {final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(60).atZone(ZoneId.systemDefault()).toInstant();
        return Jwts.builder()
                .signWith(publicKey)
                .issuer("api-getway")
                .subject(claims.getSubject())
                .claim("roles",claims.get("claims"))
                .claim("id",claims.get("id"))
                .expiration(Date.from(accessExpirationInstant))
                .compact();
    }
}
