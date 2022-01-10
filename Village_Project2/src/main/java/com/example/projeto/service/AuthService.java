package com.example.projeto.service;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.projeto.model.transport.*;

@Service
public class AuthService {

	private EmailService emailService;
	private PasswordEncoder passwordEncoder;
	private HabitanteService habitanteService;

	public AuthService(HabitanteService userService,
			PasswordEncoder passwordEncoder,
			EmailService emailService) {
		this.habitanteService = userService;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
	}

	public void sendNewPass(String email) throws SQLException  {
		VillageDTO user = habitanteService.getHabitante(email);
		if(user == null) {
			throw new RuntimeException("Email not found.");
		}
		String newPass = generatePassword();
		String encodePass = passwordEncoder.encode(newPass);
		user.setPassword(encodePass);
		habitanteService.updateUser(email, newPass);
		emailService.sendNewPassword(user, newPass);
	}
	private String generatePassword() {
		return new String(generatePassword(12));
	}
	
	
	private char[] generatePassword(int length) {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
		Random random = new Random();
		char[] password = new char[length];

		password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
		password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for (int i = 4; i < length; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
		}
		return password;
	}
}
