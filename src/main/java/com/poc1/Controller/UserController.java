package com.poc1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc1.Entity.User;
import com.poc1.Entity.UserEmail;
import com.poc1.Entity.UserRole;
import com.poc1.ServiceI.EmailServiceI;
import com.poc1.ServiceI.UserServiceI;
import com.poc1.ServiceImpl.jwtService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	UserServiceI userServiceI;
	
	@Autowired
	EmailServiceI emailService;
	
	@Autowired 
	AuthenticationManager authenticationManager;
	
	@Autowired
	private jwtService jwtService;
	
     @PostMapping("/addUser")
	public User AddUser( @Valid @RequestBody User user)
	{
    	 System.out.println("hello");
	   User user1= userServiceI.addUser(user);
		return user1;
		
	}
     
     @GetMapping("/welcome")
     public String welcome() {
    	 return "Hello Rasika";
     }
     
     @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     @GetMapping("/getAllUser")
     public List<User> getAllUser()
     {
    	 return userServiceI.getAllUser();
     }

     @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody UserRole userrole)
   {
 		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userrole.getEmail(),userrole.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(userrole.getEmail());
		}else {
			throw new UsernameNotFoundException("Invalid user request");
		}
   }
     
     @RequestMapping(value="/sendemail", method= RequestMethod.POST)
     public ResponseEntity<?> sendEmail(@RequestBody UserEmail useremail)
     {
    	 System.out.println(useremail);
    	
		return ResponseEntity.ok("Done");
    	 
     }
     
     @GetMapping("/search/{query}")
 	public List<User> searchAllData(@RequestParam ("query") String query ) {
    	 
    	 List<User> user=userServiceI.searchAllData();
		return user;
 		
 	}
}
