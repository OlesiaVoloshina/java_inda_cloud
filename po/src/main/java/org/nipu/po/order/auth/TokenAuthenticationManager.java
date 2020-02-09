package org.nipu.po.order.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof TokenAuthentication) {
            return processAuthentication((TokenAuthentication) authentication);
        } else {
            authentication.setAuthenticated(false);
            return authentication;
        }
    }

    private TokenAuthentication processAuthentication(TokenAuthentication authentication) throws AuthenticationException {
        String token = authentication.getToken();
        User user = JwtUtil.parseToken(token);
        TokenAuthentication tokenAuthentication = new TokenAuthentication(token, user.getAuthorities(), true, user);
        return tokenAuthentication;
    }
}
