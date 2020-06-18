package com.yxyk.auth.oauth;

import com.yxyk.auth.security.CustomPreAuthProvider;
import com.yxyk.auth.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	@Qualifier("preAuthProvider")
	private CustomPreAuthProvider customPreAuthProvider;


	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.withClientDetails(new ClientDetailsService() {
//			@Override
//			public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//				return null;
//			}
//		});

		//配置两个客户端,一个用于password认证一个用于client认证
		clients.withClientDetails(clientDetailsService);

//		clients.inMemory().withClient("micro-service")
//				//这里可以给不同的client分配不同的resource id,resource id可以对应一个微服务
////                .resourceIds(DEMO_RESOURCE_ID)
//				.authorizedGrantTypes("authorization_code","client_credentials", "password", "refresh_token")
//				.scopes("select")
//				.authorities("client")
//				.secret("micro-cloud")
////				.autoApprove()
////				.additionalInformation()
////				.accessTokenValiditySeconds(10)
////				.refreshTokenValiditySeconds(10)
////				.redirectUris()
//				.and()
//				.withClient("cs")
//				//这里可以给不同的client分配不同的resource id,resource id可以对应一个微服务
////                .resourceIds(DEMO_RESOURCE_ID)
//				.authorizedGrantTypes("password", "refresh_token")
//				.scopes("select","write")
//				.authorities("client")
//				.secret("cs");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
//				.addInterceptor()
//				.allowedTokenEndpointRequestMethods()
//				.approvalStore()
//				.approvalStoreDisabled()
//				.exceptionTranslator()
//				.pathMapping()
//				.prefix()
//				.requestFactory()
//				.requestValidator()
//				.reuseRefreshTokens()
//				.accessTokenConverter(accessTokenConverter())
//				.tokenStore(new JwtTokenStore(accessTokenConverter()))
				.tokenServices(tokenServices())
				.authenticationManager(authenticationManager);
	}

	@Bean
	@Primary
	public AuthorizationServerTokenServices tokenServices(){
		TokenServices tokenServices = new TokenServices();
		tokenServices.setTokenStore(new RedisTokenStore(redisConnectionFactory));

		ProviderManager providerManager = (ProviderManager) authenticationManager;
		providerManager.getProviders().add(customPreAuthProvider);

		tokenServices.setAuthenticationManager(authenticationManager);
//		tokenServices.setClientDetailsService(clientDetailsService);

		tokenServices.setSupportRefreshToken(true);

		tokenServices.setTokenEnhancer((accessToken, authentication) -> {
			DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
			if(!(authentication.getPrincipal() instanceof SecurityUser)){
				return token;
			}

			SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
//			//TODO 此时把原先的clientId kick out,生成新的clientId，保存数据库，并返回. 或者用户名用phone, 密码用access_token
//			//TODO 发消息时应该取Redis中的ClientId
//			//TODO 超时也应该kick out
//			String oldClientId = securityUser.getClientId();
//			String newClientId = Utils.generateClientId();
//			//生成mqtt中的clientId
			Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());
//			//TODO 暂时使用cs{phone}
//			if (securityUser.getPhone() != null) {
//				info.put("clientId", "cs" + securityUser.getPhone());
//			}
			token.setAdditionalInformation(info);
			return token;
		});
		return tokenServices;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		//允许表单认证
		oauthServer.allowFormAuthenticationForClients();
	}
}