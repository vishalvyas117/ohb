package com.ohb.app.service;

import java.io.StringWriter;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.ohb.app.model.User;

@Service
public class MailService {

	@Value("${app.email.from}")
	private String fromEmail;

	@Value("${app.url}")
	private String appUrl;

	@Value("${app.email.support}")
	private String supportEmail;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender jmailSender;
	
	@Autowired
	private MailContentBuilder mailContentBuilder;

	@Autowired
	public void setMailSender(JavaMailSender jmailSender) {
		this.jmailSender = jmailSender;
	}
	String message="";
	public void jsendMail(String to, String subject, String msg,User user,String url) {
		

			MimeMessagePreparator messagePreparator = mimeMessage -> {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
				String fullname= user.getFirstName()+" "+user.getLastName();
		        helper.setSubject(subject);
		        helper.setFrom(fromEmail);
				helper.setTo(to);
				helper.setText(msg, true);
				helper.setSentDate(new Date());
				//String content = mailContentBuilder.build(user);
				mimeMessage.setContent("<h2>  Dear "+fullname+" ,<br/>"
						+ " Welcome to OHB<br/> "
						+ " we are leading hotel booking organization <br/>"
						+ " your account has been been created, please click on below url for activate you account<br/>"
						+ ""+url+"</h2>"  , "text/html");
		    };
		    try {
		    	jmailSender.send(messagePreparator);

		} catch (MailException ex) {
			System.out.println("MailException : "+ex);
		}
	}

	public void sendMail(String to, String subject, String text) {
		try {
			SimpleMailMessage email = new SimpleMailMessage();

			StringWriter writer = new StringWriter();
			email.setTo(to);
			email.setSubject(subject);
			email.setFrom(fromEmail);
			email.setText(text);
			mailSender.send(email);
			System.out.println("SENT EMAIL: TO=" + to + "|SUBJECT:" + subject + "|TEXT:" + text);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void sendResetPassword(String to, String token, User user) {
		String url = appUrl + "/user/reset-password-change?token=" + token;
		String subject = "Reset Password";
		String text = user.getFirstName() + " " + user.getLastName()
				+ ", Please click the following link to reset your password: " + url;
		sendMail(to, subject, text);
	}

	public void sendNewRegistration(String to, String token, User user) {
		String url = appUrl + "user/activate?activation=" + token;
		String subject = "Welcome to OHB";
		String text = "Welcome " + user.getFirstName() + " " + user.getLastName()
				+ " thanks for registering on Ohb, Please click the following link to activate your account: " + url;
		jsendMail(to, subject, text,user,url);
	}

	public void sendNewActivationRequest(String to, String token, User user) {
		sendNewRegistration(to, token, user);
	}

	public void sendErrorEmail(Exception e, HttpServletRequest req, User user) {
		String subject = "Application Error: " + req.getRequestURL();
		String text = "An error occured in your application: " + e + "\r\nFor User:  " + user.getEmail();
		sendMail(supportEmail, subject, text);
	}
}
