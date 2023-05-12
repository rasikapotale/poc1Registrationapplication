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

//	@Autowired
//	private JwtAuthFilter authFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public UserDetailsService userDetailsService() {
//		UserDetails admin=User.withUsername("nitesh").password(passwordEncoder().encode("nitesh")).roles("ADMIN").build();
//		UserDetails normal=User.withUsername("rasika").password(passwordEncoder().encode("rasika")).roles("USER").build();
//	return new InMemoryUserDetailsManager(admin,normal);
		return new UserInfoDetailsService();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf().disable()//.authorizeHttpRequests().anyRequest().permitAll().and()
				.authorizeHttpRequests().requestMatchers("/api/user/addUser","/generate-qr" ,"/verification/verify").permitAll()
				.and()
				.authorizeHttpRequests().requestMatchers("/api/user/**")
				.authenticated()
				.and()
				.formLogin()
				.and()
				.build();
	}
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		return httpSecurity
//		.csrf().disable()
//		.authorizeHttpRequests()
//	  	.requestMatchers("/welcome","/token/authenticate").permitAll()
//	  	.and()
//	  	.authorizeHttpRequests()
//	  	.requestMatchers("/api/user/save")
//	  	.authenticated()
//	  	.and()
//	  	.sessionManagement()
//	  	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	  	.and()
//	  	.authenticationProvider(authenticationProvider())
//	  	.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();
//	}
//	
//	@Bean
//	public UserDetailsService userDetailsService()
//	{
//		return new UserInfoDetailsService();
//		
//	}
//	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
//
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration configs) throws Exception {
//		return configs.getAuthenticationManager();
//	}
	
}
