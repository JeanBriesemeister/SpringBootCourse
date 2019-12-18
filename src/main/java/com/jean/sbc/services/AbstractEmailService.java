package com.jean.sbc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.jean.sbc.domain.Customer;
import com.jean.sbc.domain.Order;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(Order order) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(order);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order order) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(order.getCustomer().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Order confirmed! Id: " + order.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(order.toString());

		return sm;
	}

	protected String htmlFromTemplateOrder(Order order) {
		Context context = new Context();
		context.setVariable("order", order);

		return templateEngine.process("email/orderConfirmation", context);
	}

	@Override
	public void sendOrderConfirmatioHtmlEmail(Order order) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromOrder(order);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(order);
		}
	}

	@Override
	public void sendNewPasswordEmail(Customer customer, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(customer, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Customer customer, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(customer.getEmail());
		sm.setFrom(sender);
		sm.setSubject("New password request ");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("New password: " + newPass);

		return sm;
	}

	protected MimeMessage prepareMimeMessageFromOrder(Order order) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(order.getCustomer().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Order confirmed! Id: " + order.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateOrder(order), true);

		return mimeMessage;
	}

}
