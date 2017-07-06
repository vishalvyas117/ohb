package com.ohb.app.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailNotificationService {
	
	public void sendUserAdditionConfirmation(String name, String email, String bookingUrl);
}
