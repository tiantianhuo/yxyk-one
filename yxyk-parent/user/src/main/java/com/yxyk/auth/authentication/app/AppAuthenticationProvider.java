package com.yxyk.auth.authentication.app;

import com.yxyk.auth.security.SecurityUser;
import com.yxyk.auth.service.AppUserService;
import com.yxyk.user.bean.po.AppUserInfo;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Deng
 * @date 2019-08-23
 */
public class AppAuthenticationProvider implements AuthenticationProvider {

    @Setter
    private AppUserService userService;

    @Setter
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AppAuthenticationToken authenticationToken = (AppAuthenticationToken) authentication;

        String username = (String) authenticationToken.getPrincipal();
        String password = (String) authenticationToken.getCredentials();

        AppUserInfo local = userService.findUserByMobileAndStatus(username, 0);

        if (local == null) {

            throw new BadCredentialsException(username);
        }

        boolean match = passwordEncoder.matches(password, local.getPassword());
        if (!match) {

            throw new BadCredentialsException(username);
        } else {

            SecurityUser securityUser = new SecurityUser(local);
            return new AppAuthenticationToken(securityUser,  authenticationToken.getCredentials(), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_app"));
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AppAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
