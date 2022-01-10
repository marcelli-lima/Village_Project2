package com.example.projeto.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserSpringSecurity implements UserDetails {

private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSpringSecurity() {
	}
	
	public UserSpringSecurity(String email, String password, Collection<? extends GrantedAuthority> authorities) {
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	public UserSpringSecurity(String email, String password, Set<String> authorities) {
		this.email = email;
		this.password = password;
		this.authorities = authorities.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toSet());
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
