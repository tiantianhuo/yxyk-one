package com.yxyk.auth.oauth;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class MyClientDetailService implements ClientDetailsService{
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		if(clientId.equals("micro-service")){
			return new ClientBuilder("micro-service")
					.authorizedGrantTypes("client_credentials", "password", "refresh_token")
					.scopes("select")
					.authorities("ROLE_server")
					.secret("micro-supply")
					.build();
		} else if(clientId.equals("supply")){
			return new ClientBuilder("supply")
					.authorizedGrantTypes("password", "refresh_token")
					.scopes("select","write")
					.authorizedGrantTypes("password")
					.authorities("client")
					.secret("supply")
					.build();
		} else if (clientId.equals("supply_app")) {
			return new ClientBuilder("supply_app")
					.authorizedGrantTypes("password", "refresh_token")
					.scopes("select", "write")
					.authorizedGrantTypes("password")
					.authorities("client")
					.secret("supply_app")
					.build();
		} else {
			throw new ClientRegistrationException("client: " + clientId + " not found");
		}
	}

	public final class ClientBuilder {
		private final String clientId;

		private Collection<String> authorizedGrantTypes = new LinkedHashSet<String>();

		private Collection<String> authorities = new LinkedHashSet<String>();

		private Integer accessTokenValiditySeconds;

		private Integer refreshTokenValiditySeconds;

		private Collection<String> scopes = new LinkedHashSet<String>();

		private Collection<String> autoApproveScopes = new HashSet<String>();

		private String secret;

		private Set<String> registeredRedirectUris = new HashSet<String>();

		private Set<String> resourceIds = new HashSet<String>();

		private boolean autoApprove;

		private Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();

		private ClientDetails build() {
			BaseClientDetails result = new BaseClientDetails();
			result.setClientId(clientId);
			result.setAuthorizedGrantTypes(authorizedGrantTypes);
			result.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
			result.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
			result.setRegisteredRedirectUri(registeredRedirectUris);
			result.setClientSecret(secret);
			result.setScope(scopes);
			result.setAuthorities(AuthorityUtils.createAuthorityList(authorities.toArray(new String[authorities.size()])));
			result.setResourceIds(resourceIds);
			result.setAdditionalInformation(additionalInformation);
			if (autoApprove) {
				result.setAutoApproveScopes(scopes);
			}
			else {
				result.setAutoApproveScopes(autoApproveScopes);
			}
			return result;
		}

		public ClientBuilder resourceIds(String... resourceIds) {
			for (String resourceId : resourceIds) {
				this.resourceIds.add(resourceId);
			}
			return this;
		}

		public ClientBuilder redirectUris(String... registeredRedirectUris) {
			for (String redirectUri : registeredRedirectUris) {
				this.registeredRedirectUris.add(redirectUri);
			}
			return this;
		}

		public ClientBuilder authorizedGrantTypes(String... authorizedGrantTypes) {
			for (String grant : authorizedGrantTypes) {
				this.authorizedGrantTypes.add(grant);
			}
			return this;
		}

		public ClientBuilder accessTokenValiditySeconds(int accessTokenValiditySeconds) {
			this.accessTokenValiditySeconds = accessTokenValiditySeconds;
			return this;
		}

		public ClientBuilder refreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
			this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
			return this;
		}

		public ClientBuilder secret(String secret) {
			this.secret = secret;
			return this;
		}

		public ClientBuilder scopes(String... scopes) {
			for (String scope : scopes) {
				this.scopes.add(scope);
			}
			return this;
		}

		public ClientBuilder authorities(String... authorities) {
			for (String authority : authorities) {
				this.authorities.add(authority);
			}
			return this;
		}

		public ClientBuilder autoApprove(boolean autoApprove) {
			this.autoApprove = autoApprove;
			return this;
		}

		public ClientBuilder autoApprove(String... scopes) {
			for (String scope : scopes) {
				this.autoApproveScopes.add(scope);
			}
			return this;
		}

		public ClientBuilder additionalInformation(Map<String, ?> map) {
			this.additionalInformation.putAll(map);
			return this;
		}

		public ClientBuilder additionalInformation(String... pairs) {
			for (String pair : pairs) {
				String separator = ":";
				if (!pair.contains(separator) && pair.contains("=")) {
					separator = "=";
				}
				int index = pair.indexOf(separator);
				String key = pair.substring(0, index > 0 ? index : pair.length());
				String value = index > 0 ? pair.substring(index+1) : null;
				this.additionalInformation.put(key, (Object) value);
			}
			return this;
		}



		private ClientBuilder(String clientId) {
			this.clientId = clientId;
		}

	}
}
