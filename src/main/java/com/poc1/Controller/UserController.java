package com.poc1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc1.Entity.User;
import com.poc1.ServiceI.UserServiceI;

@RestController
public class UserController {
	
	@Autowired
	UserServiceI userServiceI;
@PostMapping("/AddUser")
	public User AddUser(@RequestBody User user)
	{
	User user1= userServiceI.addUser(user);
		return user1;
		
	}
}
