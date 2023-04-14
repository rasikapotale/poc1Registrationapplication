package com.poc1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.poc1.Entity.UserRole;
import com.poc1.repository.UserRoleRepository;

@SpringBootApplication
public class Poc1Application implements CommandLineRunner{

	@Autowired
	UserRoleRepository userRoleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Poc1Application.class, args);
	}

	@Override 
	public void run(String... args) throws Exception {
	userRoleRepository.save(new UserRole("rasika@gmail.com",PasswordEncoders().encode("123"), "ROLE_ADMIN"));
		
	}

	@Bean
	public PasswordEncoder PasswordEncoders() {
		return new BCryptPasswordEncoder();
	}
	
	
}
