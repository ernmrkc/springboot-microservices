package org.ernmrkc.customerservice.Security.Models;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;
    private final String token;

    public JwtAuthenticationToken(String token){
        super(AuthorityUtils.NO_AUTHORITIES);
        this.token = token;
        this.principal = null;
        setAuthenticated(false);
    }

    public JwtAuthenticationToken(String username, String token){
        super(AuthorityUtils.NO_AUTHORITIES);
        this.principal = username;
        this.token = token;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
