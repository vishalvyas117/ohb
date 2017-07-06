package com.ohb.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import com.ohb.app.model.User;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

	private TemplateEngine templateEngine;
	
	
	@Value("${app.url}")
	private String appUrl;
	
	@Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
	public String build(User user) {
        Context context = new Context();
        String fullname= user.getFirstName()+" "+user.getLastName();
        String url = appUrl + "/user/reset-password-change?token=" + user.getToken();
        context.setVariable("fullname", fullname);
        context.setVariable("email", user.getEmail());
        context.setVariable("password", user.getPassword());
        context.setVariable("url", url);
        return templateEngine.process("activeacc", context);
    }
}
