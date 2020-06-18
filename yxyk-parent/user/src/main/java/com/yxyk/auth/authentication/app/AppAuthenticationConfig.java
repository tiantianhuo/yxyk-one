package com.yxyk.auth.authentication.app;

import com.yxyk.auth.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Deng
 * @date 2019-08-23
 */
@Component
public class AppAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Resource
    private AuthenticationSuccessHandler userAuthenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandler userAuthenticationFailureHandler;

    @Autowired
    private AppUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        AppAuthenticationFilter filter = new AppAuthenticationFilter();
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationSuccessHandler(userAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(userAuthenticationFailureHandler);

        AppAuthenticationProvider provider = new AppAuthenticationProvider();

        provider.setUserService(userService);
        provider.setPasswordEncoder(passwordEncoder);

        http.authenticationProvider(provider)
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }


}
