package com.poc1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmailController {

//	@Autowired
//	private EmailService emailService;
//	
//	@Value("${spring.mail.username}")
//	String fromEmail;
//	
//	@PostMapping("/seneEmail")
//	public String emailSend(@RequestBody Email email) {
//		email.setFromEmail(fromEmail);
//		try {
//			emailService.emailSend(email);
//		} catch (Exception e) {
//			e.getMessage();
//			return "Error while send Email";
//		}
//		return "Email send Successully";
//	}
}
