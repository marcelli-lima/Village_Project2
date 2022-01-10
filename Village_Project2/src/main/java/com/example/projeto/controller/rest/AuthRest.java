package com.example.projeto.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.projeto.util.*;
import com.example.projeto.service.*;
import com.example.projeto.model.transport.JwtDTO;
import com.example.projeto.model.transport.MailDTO;
import com.google.gson.Gson;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.projeto.model.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@RestController
@RequestMapping("auth")
public class AuthRest {


	private JWTUtil jwtUtil;
	
	private AuthService authService;
	
	public AuthRest(JWTUtil jwtUtil, AuthService authService) {
		this.jwtUtil = jwtUtil;
		this.authService = authService;
	}

	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) throws IOException{
		UserSpringSecurity userSpringSecurity = HabitanteService.authenticated();
		JwtDTO generateToken = jwtUtil.generateToken(userSpringSecurity.getUsername());
		response.addHeader("Authorization", generateToken.getFullToken());
		response.getWriter().append(new Gson().toJson(generateToken));
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@RequestBody MailDTO mail) throws SQLException{
		authService.sendNewPass(mail.getEmail());
		return ResponseEntity.noContent().build();
	}

}
