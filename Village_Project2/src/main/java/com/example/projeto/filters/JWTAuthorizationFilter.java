package com.example.projeto.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.projeto.service.HabitanteService;
import com.example.projeto.util.*;
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	private HabitanteService habitanteService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
			HabitanteService habitanteService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.habitanteService = habitanteService;
	}

	 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws IOException, ServletException {

	        String header = request.getHeader("Authorization");
	        validateToken(header);
	        chain.doFilter(request, response);
	    }

	    private void validateToken(String header) {
	        if (header == null) {
	            return;
	        }

	        if (!header.startsWith("Bearer ")) {
	            return;
	        }
	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                getAuthentication(header.substring(7));

	        if (usernamePasswordAuthenticationToken == null) {
	            return;
	        }

	        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	    }

	    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
	        if (!jwtUtil.validateToken(token)) {
	            return null;
	        }

	        String username = jwtUtil.getTokenSubject(token);
	        UserDetails user = habitanteService.loadUserByUsername(username);
	        return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
	    }
}
