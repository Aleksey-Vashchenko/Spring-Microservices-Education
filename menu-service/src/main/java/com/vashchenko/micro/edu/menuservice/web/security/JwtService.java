package com.vashchenko.micro.edu.menuservice.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class JwtService {

    public JwtAuthentication authenticate(String token) {
        Claims claims = getClaims(token);
        JwtAuthentication jwtAuthentication = JwtAuthentication.builder()
                .authenticated(true)
                .email((String) claims.get("email"))
                .username((String) claims.get("username"))
                .name((String) claims.get("name"))
                .uuid((UUID) claims.get("uuid"))
                .roles(convertRolesFromStringList((List<String>) claims.get("roles")))
                .build();
        return jwtAuthentication;
    }

    private Claims getClaims(String token){
       try {
            return Jwts.parser()
                    .build()
                    .parseClaimsJwt(token)
                    .getBody();
       } catch (ExpiredJwtException e) {
            return e.getClaims();
       }
    }

    private static Set<GrantedAuthority> convertRolesFromStringList(List<String> roleStrings) {
        Set<GrantedAuthority> roles = new HashSet<>();

        for (String roleString : roleStrings) {
            GrantedAuthority role = new SimpleGrantedAuthority(roleString);
            roles.add(role);
        }
        return roles;
    }
}
