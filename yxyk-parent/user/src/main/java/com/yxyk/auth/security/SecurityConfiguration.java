package com.yxyk.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author TieYan
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("myAuthenticationProvider")
    private AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier("preAuthProvider")
    private AuthenticationProvider preAuthProvider;


    @Bean
    public UserDetailsService myUserDetailsService(){
        return new MyUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        //userService 是用来覆写 loadUserByUsername方法的
        auth
                .authenticationProvider(authenticationProvider)
                .authenticationProvider(preAuthProvider)
                .userDetailsService(myUserDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests()
                .antMatchers("/oauth/*","/code", "/user/mine").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
        // @formatter:on
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/druid/**");
//                .and().ignoring().antMatchers("/profile/**");
    }
}