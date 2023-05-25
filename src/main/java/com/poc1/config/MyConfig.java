package com.poc1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity 
@EnableWebSecurity
public class MyConfig {

	@Autowired
	private JwtAuthFilter authFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}	
	
	@Bean
	public UserDetailsService userDetailsService() {

		return new UserInfoDetailsService();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf().disable()
				.authorizeHttpRequests().requestMatchers("/token/**" ,"/verification/verify").permitAll()
				.and()
				.authorizeHttpRequests().requestMatchers("/api/user/**","/audit-log/**","/api/company/**")
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configs) throws Exception {
		return configs.getAuthenticationManager();
	}
	
}
