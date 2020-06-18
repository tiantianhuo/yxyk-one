package com.yxyk.auth.oauth;

import com.yxyk.auth.authentication.app.AppAuthenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Deng
 * @date 2019/07/23
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	private AppAuthenticationConfig appAuthenticationConfig;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
				.and()
				.apply(appAuthenticationConfig)
				.and()
				.requestMatchers().anyRequest()
				.and()
				.anonymous()
				.and()
				.authorizeRequests()
//                    .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
				.antMatchers("/user", "/oauth/logout", "/admin").authenticated()//配置order访问控制，必须认证过后才可以访问
				.and()
				.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and()
				.authorizeRequests()
				.antMatchers("/login", "/user/**","/tUser/**","/suWarehouse/**","/suTemplate/**","/suStorage/**","/suMerchant/**","/tOrgunit/**","/key/**")
				.permitAll()
				.antMatchers("/suCost/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic().disable();
	}
}