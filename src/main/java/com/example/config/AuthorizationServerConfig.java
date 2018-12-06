package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	/** Token valid for 1 days */
	private static final int TOKEN_VALIDITY_SECONDS = 1 * 24 * 3600;

	@Value("${config.privateKey}")
	private String privateKey;

	@Value("${config.publicKey}")
	private String publicKey;

	@Value("${config.clientId}")
	private String clientId;

	@Value("${config.secret}")
	private String secret;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		return converter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenEnhancer());
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices services = new DefaultTokenServices();
		services.setTokenStore(tokenStore());
		services.setAccessTokenValiditySeconds(TOKEN_VALIDITY_SECONDS);
		return services;
	}

	/**
	 * Defines the security constraints on the token endpoints /oauth/token_key
	 * and /oauth/check_token Client credentials are required to access the
	 * endpoints
	 *
	 * @param oauthServer
	 *            Security configurer
	 * @throws Exception
	 *             when has exception
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_CLIENT')")
				.checkTokenAccess("hasAuthority('ROLE_CLIENT')");

	}

	/**
	 * Defines the authorization and token endpoints and the token services
	 *
	 * @param endpoints
	 *            Server endpoints configurer
	 * @throws Exception
	 *             when has exception
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).accessTokenConverter(tokenEnhancer());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientId).secret(bCryptPasswordEncoder.encode(secret)).authorities("ROLE_CLIENT")
				.authorizedGrantTypes("client_credentials", "password", "authorization_code", "refresh_token")
				.scopes("read", "write").accessTokenValiditySeconds(TOKEN_VALIDITY_SECONDS);

	}

}
