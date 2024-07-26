package com.vashchenko.micro.edu.menuservice.web.security;

import lombok.SneakyThrows;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JwtService {

    public JwtAuthentication authenticate(String token){
        JwtClaims claims = getClaims(token);
        JwtAuthentication jwtAuthentication = JwtAuthentication.builder()
                .authenticated(true)
                .email((String) claims.getClaimValue("email"))
                .username((String) claims.getClaimValue("username"))
                .name((String) claims.getClaimValue("name"))
                .uuid((String) claims.getClaimValue("uuid"))
                .roles(convertRolesFromStringList((List<String>) claims.getClaimValue("roles")))
                .build();
        return jwtAuthentication;
    }

    @SneakyThrows
    private JwtClaims getClaims(String token){
        JwtConsumer consumer = new JwtConsumerBuilder()
                .setSkipAllValidators()
                .setDisableRequireSignature()
                .setSkipSignatureVerification()
                .build();
        return consumer.processToClaims(token);
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
