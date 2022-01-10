package com.example.projeto.service;

import java.util.Date;

import org.springframework.mail.SimpleMailMessage;

import com.example.projeto.model.transport.VillageDTO;

public abstract class AbstractEmailService implements EmailService {

	private String sender;

	public AbstractEmailService(String sender) {
		this.sender = sender;
	}

	@Override
	public void sendNewPassword(VillageDTO user, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(user, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(VillageDTO user, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}

}
