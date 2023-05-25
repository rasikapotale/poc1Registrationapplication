package com.poc1.config;

import org.aspectj.weaver.ast.And;
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
<<<<<<< HEAD
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
=======
>>>>>>> cf7145f0dabce11b8351b1876f6542778878ce5b
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
<<<<<<< HEAD
=======

import com.poc1.ServiceImpl.UserInfoDetailsService;
import com.poc1.ServiceImpl.jwtService;
import com.poc1.filter.jwtUserFilter;
>>>>>>> cf7145f0dabce11b8351b1876f6542778878ce5b

@Configuration
@EnableMethodSecurity 
@EnableWebSecurity
public class MyConfig {
<<<<<<< HEAD

	@Autowired
	private JwtAuthFilter authFilter;
=======
	
     @Autowired
	private jwtUserFilter jwtUserFilter;
     
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		
		
		httpSecurity
		.csrf().disable()
		.authorizeHttpRequests()
		//everyone can use without authenticate
//		.requestMatchers("/addUser","/authenticate")		
//		.permitAll()
//		.and()
//		.authorizeHttpRequests()
//		.requestMatchers("/getAllUser","/welcome")
//		.authenticated()	
		.anyRequest().permitAll()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider())
		.addFilterBefore(jwtUserFilter, UsernamePasswordAuthenticationFilter.class);

		
		return httpSecurity.build();
		
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new UserInfoDetailsService();
		
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
>>>>>>> cf7145f0dabce11b8351b1876f6542778878ce5b
	
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
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
