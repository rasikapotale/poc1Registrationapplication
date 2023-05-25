package com.poc1.ServiceI;

import javax.mail.MessagingException;

public interface EmailServiceI {

	public boolean sendEmail(String subject, String message,String to) throws MessagingException;
	
}
