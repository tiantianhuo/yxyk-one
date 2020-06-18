package com.yxyk.auth.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomPreAuthUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Override
	public final UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) {
    	if(token.getPrincipal() instanceof UsernamePasswordAuthenticationToken){
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) token.getPrincipal();
			SecurityUser securityUser = (SecurityUser) usernamePasswordAuthenticationToken.getPrincipal();
			return securityUser;
		}else{
    		return null;
		}
    }
}