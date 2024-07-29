package com.vashchenko.micro.edu.apigateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;

@Service
public class IncomeJwtVerifier {
    private final Key publicKey;

    public IncomeJwtVerifier(@Value("${jwt.income.secret}") String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.publicKey= Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims getValidatedClaims(String token) throws MalformedJwtException,JwtException {
        Claims claims = Jwts.parser().setSigningKey(publicKey).build().parseSignedClaims(token).getPayload();
        if(!validateCredentialClaims(claims)){
            throw new MalformedJwtException("Jwt token does not contain necessary credentials");
        }
        return claims;
    }

    private boolean validateCredentialClaims(Claims claims) {
        return claims.get("roles") != null && claims.get("subject") != null && claims.get("name") != null;
    }
}
