package com.example.config;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.dao.entity.User;
import com.example.dao.entity.UserRole;
import com.example.dao.repository.RoleRepository;
import com.example.dao.repository.UserRepository;
import com.example.dao.repository.UserRoleRepository;

public class AuthenticationImpl implements AuthenticationProvider {

	private UserRepository usersRepository;

	private RoleRepository roleRepository;

	private UserRoleRepository userRoleRepository;

	public AuthenticationImpl(UserRepository usersRepository, RoleRepository roleRepository,
			UserRoleRepository userRoleRepository) {
		this.usersRepository = usersRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// Check authentication
		String account = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		User user = usersRepository.findByAccount(account);
		if (user == null) {
			StringBuffer msg = new StringBuffer("Couldn't find user : ");
			throw new UsernameNotFoundException(msg.append(account).append(" ").append(" in the system").toString());
		}

		if (!BCrypt.checkpw(pwd, user.getPwd())) {
			throw new ValidationException("Password was wrong");
		}

		List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (userRoles != null) {
			userRoles.forEach(role -> {
				GrantedAuthority authority = new SimpleGrantedAuthority(roleRepository.findById(role.getId()).get().getName());
				grantList.add(authority);
			});
		}
		// Generate token
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(account, pwd, grantList);

		return token;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}