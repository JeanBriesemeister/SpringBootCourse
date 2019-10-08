package com.jean.sbc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.jean.sbc.domain.Request;

public interface EmailService {

	void sendOrderConfirmationEmail(Request request);

	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmatioHtmlEmail(Request request);

	void sendHtmlEmail(MimeMessage msg);
}
