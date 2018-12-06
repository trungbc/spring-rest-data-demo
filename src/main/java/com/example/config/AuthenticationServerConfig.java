package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.example.dao.repository.RoleRepository;
import com.example.dao.repository.UserRepository;
import com.example.dao.repository.UserRoleRepository;

/**
 * Authentication configuration
 */
@Configuration
@EnableWebSecurity
@EnableResourceServer
public class AuthenticationServerConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.NEVER);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Ignore monitoring endpoints
		web.ignoring().antMatchers("/admin/**");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(new AuthenticationImpl(usersRepository, roleRepository, userRoleRepository));
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

}
