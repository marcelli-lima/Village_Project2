package com.example.projeto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.projeto.service.EmailService;
import com.example.projeto.service.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Bean
	public EmailService emailService() {
		return (EmailService) new MockEmailService("Test Sender");
	}
}
