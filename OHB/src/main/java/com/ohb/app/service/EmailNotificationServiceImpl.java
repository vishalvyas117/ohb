package com.ohb.app.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.ohb.app.model.User;

@Service
public class EmailNotificationServiceImpl {

	
	@Value("${app.email.from}")
	private String fromEmail;

	@Value("${app.url}")
	private String appUrl;

	@Value("${app.email.support}")
	private String supportEmail;
	public static final String EMAIL_SUBJECT = "Your OHB has been Created";
	
	private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;

    public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
    
    public void sendAccountActivationConfirmation(User user, String email, String accountUrl) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void prepare(MimeMessage mimeMessage) throws Exception {
			     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			     message.setTo(email);
	             message.setBcc(supportEmail);
			     message.setFrom(fromEmail);
			     message.setSubject(EMAIL_SUBJECT);
			     message.setSentDate(new Date());

			     Map model = new HashMap();
			     model.put("firstName", user.getFirstName());
			     model.put("lastName", user.getLastName());
			     model.put("accountUrl", accountUrl);

			     @SuppressWarnings("deprecation")
				String text = VelocityEngineUtils.mergeTemplateIntoString(
			        velocityEngine, "velocity/account_activation_notification.vm", "UTF-8", model);

			     message.setText(text, true);

			}
		};
		       mailSender.send(preparator);
	}


}
