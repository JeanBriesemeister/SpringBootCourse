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

import com.jean.sbc.domain.Request;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(Request request) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(request);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Request request) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(request.getCustomer().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Order confirmed! Id: " + request.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(request.toString());

		return sm;
	}

	protected String htmlFromTemplateOrder(Request request) {
		Context context = new Context();
		context.setVariable("request", request);

		return templateEngine.process("email/orderConfirmation", context);
	}

	@Override
	public void sendOrderConfirmatioHtmlEmail(Request request) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromOrder(request);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(request);
		}
	}

	protected MimeMessage prepareMimeMessageFromOrder(Request request) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(request.getCustomer().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Order confirmed! Id: " + request.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateOrder(request), true);

		return mimeMessage;
	}

}
