package com.poc1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc1.config.JwtService;
import com.poc1.model.UserRole;
import com.poc1.repository.UserRoleRepository;

@RestController
@RequestMapping("/token")
public class LoginController {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/authenticate")
	public String authentication(@RequestBody UserRole role) {
//		List<UserRole> roles=userRoleRepository.findAll();
//		
			Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(role.getEmail(),role.getPassword()));
			if(authentication.isAuthenticated()) {
				return jwtService.generateToken(role.getEmail());
			}else {
				throw new UsernameNotFoundException("Invalid user request");
			}
//			
	}

}
