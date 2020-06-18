package com.yxyk.auth.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider, Serializable {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    // ...
}