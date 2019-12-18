package com.jean.sbc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.jean.sbc.domain.Customer;
import com.jean.sbc.domain.Order;

public interface EmailService {

	void sendOrderConfirmationEmail(Order order);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmatioHtmlEmail(Order order);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Customer customer, String newPass);
}
