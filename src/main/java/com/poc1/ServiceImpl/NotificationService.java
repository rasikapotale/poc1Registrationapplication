package com.poc1.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	
	@Autowired
	JavaMailSender mailSender;
	
	
//    @Value("${twilio.accountSid}")
//    private String accountSid;
//
//    @Value("${twilio.authToken}")
//    private String authToken;
//
//    @Value("${twilio.fromNumber}")
//    private String fromNumber;
//    
//    public void sendSMS(String to,String body) {
//    	Twilio.init(accountSid, authToken);
//    	Message message=Message.creator(new PhoneNumber(to), new PhoneNumber(fromNumber), body).create();
//    }
    
	public void sendEmail(String toEmail,String subject,String textBody) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(textBody);
		mailSender.send(message);
		
	}

}
