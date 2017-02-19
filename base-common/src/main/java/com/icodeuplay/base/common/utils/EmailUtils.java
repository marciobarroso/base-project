package com.icodeuplay.base.common.utils;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.icodeuplay.base.common.exceptions.BaseCommonException;

public class EmailUtils implements Serializable {

	private static final long serialVersionUID = -3762766604289900564L;

	public static final String CONTENT_TYPE_PLAIN = "text/plain";
	public static final String CONTENT_TYPE_HTML = "text/html";

	private Properties properties;
	private Authenticator authenticator;
	private String username;
	private String password;

	public EmailUtils(String username, String password) {
		this.username = username;
		this.password = password;

		this.properties = new Properties();
		this.properties.put("mail.smtp.auth", "true");
		this.properties.put("mail.smtp.starttls.enable", "true");
		this.properties.put("mail.smtp.host", "webmail.cpmbraxis.com");
		this.properties.put("mail.imap.port", "993");

		this.authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(getUsername(), getPassword());
			};
		};
	}

	public void send(String from, String[] to, String subject, String message, String contentType) {
		Session session = Session.getDefaultInstance(this.getProperties(), this.getAuthenticator());

		try {
			Message mail = new MimeMessage(session);
			mail.setFrom(new InternetAddress(from));

			InternetAddress[] toAddresses = new InternetAddress[to.length];

			for (int i = 0; i < to.length; i++) {
				toAddresses[i] = new InternetAddress(to[i]);
			}

			// mail.setRecipient(Message.RecipientType.BCC, new
			// InternetAddress("marcio.barroso@capgemini.com"));
			mail.setRecipients(Message.RecipientType.TO, toAddresses);
			mail.setSubject(subject);
			mail.setContent(message, contentType);

			Transport.send(mail);

		} catch (Exception e) {
			throw new BaseCommonException(e);
		}
	}

	public Authenticator getAuthenticator() {
		return authenticator;
	}

	public Properties getProperties() {
		return properties;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}