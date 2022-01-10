package com.example.projeto.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    private MailSender mailSender;

    public SmtpEmailService(String sender, MailSender mailSender){
        super(sender);
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOG.info("Enviando email...");
        mailSender.send(message);
        LOG.info("Email enviado");
    }
}
