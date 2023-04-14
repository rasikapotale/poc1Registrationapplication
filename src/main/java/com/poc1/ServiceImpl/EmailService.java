package com.poc1.ServiceImpl;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poc1.Entity.User;
import com.poc1.ServiceI.EmailServiceI;



@Service
public class EmailService implements EmailServiceI{

	
	
	@Override
	public boolean sendEmail(String subject, String message, String to)  {
		
		boolean f= false;
		
		String from= "rasikapotale99@gmail.com";
		String host="smtp.gmail.com";
		
		Properties properties= System.getProperties();
		System.out.println("properties"+properties);
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.auth", "true");
		
	Session session=Session.getInstance(properties, new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication()
		{
			return new PasswordAuthentication("rasikapotale99@gmail.com", "Rasika@2411");
		}
		
	
	});
	session.setDebug(true);
	MimeMessage m= new MimeMessage(session);
	try {
		m.setFrom(from);
		m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		m.setSubject(subject);
		m.setText(message);
		Transport.send(m);
		
		System.out.println("sent sucessfully.....");
		f=true;
	} catch (MessagingException e) {
		
		e.printStackTrace();
	}
	
	return f;
	}
  
	
}
