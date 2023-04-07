package com.poc1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc1.Entity.User;
import com.poc1.ServiceI.UserServiceI;

@RestController
public class UserController {
	
	@Autowired
	UserServiceI userServiceI;
	
	
     @PostMapping("/addUser")
	public User AddUser( @RequestBody User user)
	{
    	 System.out.println("hello");
	   User user1= userServiceI.addUser(user);
		return user1;
		
	}
     
     @GetMapping("/welcome")
     public String welcome() {
    	 return "Hello Rasika";
     }
     
     @PreAuthorize("hasRole('admin')")
     @GetMapping("/getAllUser")
     public List<User> getAllUser()
     {
    	 return userServiceI.getAllUser();
     }
}
