package com.bank.dms.job;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class Mail {
	private JavaMailSender mailSender;

	public JavaMailSender getMailSender() {
		return mailSender;
	}
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void send(final String from, final String[] to, final String subject, final String msg, final String attachName, final File attach) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(msg);
		helper.addAttachment(MimeUtility.encodeText(attachName, "UTF-8", "B"), attach);
		mailSender.send(message);
	}
	public void send(final String from, final String[] to, final String subject, final String msg, final String attachName, final InputStream in) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(msg);
		helper.addAttachment(MimeUtility.encodeText(attachName, "UTF-8", "B"), new InputStreamResource(in));
		mailSender.send(message);
	}
	public void send(final InternetAddress from, final InternetAddress[] to, final String subject, final String msg, final String attachName, final InputStream in) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(msg);
		helper.addAttachment(MimeUtility.encodeText(attachName, "UTF-8", "B"), new InputStreamResource(in));
		mailSender.send(message);
	}
	public void send(final String from, final String[] to, final String subject, final String msg) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(msg);
        mailSender.send(message);
    }
}
