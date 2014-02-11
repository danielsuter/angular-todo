package ch.daniel.todobackend.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ch.daniel.todobackend.dao.ApplicationProperties;

@Stateless
public class MailService {
	private static final String FROM = "info@shinseikan.ch";

	@Resource(lookup = "mail/shinseikan")
	private Session mailSession;

	public void send(String to, String subject, String message) {
		try {
			Message mimeMessage = new MimeMessage(mailSession);
			mimeMessage.setFrom(new InternetAddress(FROM));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);
			
			if(ApplicationProperties.isTestMode()) {
				System.out.println(message + "\n --> to " + to);
			} else {
				Transport.send(mimeMessage);
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
