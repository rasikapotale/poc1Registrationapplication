package com.poc1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.poc1.model.*;
import com.poc1.config.MyConfig;
import com.poc1.config.TwilioConfig;
import com.poc1.repository.UserRoleRepository;


@SpringBootApplication
public class Poc1Application implements CommandLineRunner{

	@Autowired
	private UserRoleRepository userRoleRepository;
//	@Autowired 
//	private TwilioConfig twilioConfig;
	
	@Autowired 
	private MyConfig myConfig;
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Poc1Application.class, args);
	}

//	@PostConstruct
//	public void initTwilio() {
//		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
//	}
	

	
	@Override
	public void run(String... args) throws Exception {
	userRoleRepository.save(new UserRole("rasika@gmail.com",myConfig.passwordEncoder().encode("123"), "ROLE_ADMIN"));
	userRoleRepository.save(new UserRole("nitesh@gmail.com",myConfig.passwordEncoder().encode("123"), "ROLE_USER"));	
	}
	
	
}
