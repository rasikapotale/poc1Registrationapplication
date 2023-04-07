package com.poc1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.poc1.Entity.UserInfoDetails;
import com.poc1.ServiceImpl.UserInfoDetailsService;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class MyConfig {

	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		
		httpSecurity
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/addUser","/welcome")		
		.permitAll()
		.requestMatchers("/getAllUser")
		.hasRole("admin")
		.anyRequest()
		.authenticated()			
		.and()
		.formLogin();	
		return httpSecurity.build();
		
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new UserInfoDetailsService();
		
	}
	
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
}
