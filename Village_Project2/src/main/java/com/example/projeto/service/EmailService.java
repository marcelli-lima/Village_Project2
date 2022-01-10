package com.example.projeto.service;

import org.springframework.mail.SimpleMailMessage;

import com.example.projeto.model.transport.VillageDTO;

public interface EmailService {


	void sendEmail(SimpleMailMessage message);
	
	 void sendNewPassword(VillageDTO user, String newPassword);
}
