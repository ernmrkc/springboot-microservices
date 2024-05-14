package org.ernmrkc.customerservice.Security;

import org.ernmrkc.customerservice.Helpers.JwtUtil;
import org.ernmrkc.customerservice.Security.Models.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Collections;

public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
        String token = (String) jwtToken.getCredentials();

        if (!JwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Invalid JWT Token");
        }

        String username = JwtUtil.extractUsername(token);
        if (username == null) {
            throw new IllegalArgumentException("JWT Token does not contain a valid subject");
        }

        // TODO: StackOverflowError
        // return new JwtAuthenticationToken(username, token);

        return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
