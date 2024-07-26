package com.vashchenko.micro.edu.menuservice.web.security;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class JwtAuthentication implements Authentication {
    private boolean authenticated;
    private String username;
    private String email;
    private String name;
    private UUID uuid;
    private Set<GrantedAuthority> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return roles; }

    @Override
    public Object getCredentials() { return null; }

    @Override
    public Object getDetails() { return null; }

    @Override
    public Object getPrincipal() { return username; }

    @Override
    public boolean isAuthenticated() { return authenticated; }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() { return name; }
}
