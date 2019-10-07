package com.jean.sbc.services;

import org.springframework.mail.SimpleMailMessage;

import com.jean.sbc.domain.Request;

public interface EmailService {

	void sendOrderConfirmationEmail(Request request);

	void sendEmail(SimpleMailMessage msg);
}
