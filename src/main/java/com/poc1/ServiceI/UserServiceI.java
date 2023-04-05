package com.poc1.ServiceI;

import org.springframework.stereotype.Service;

import com.poc1.Entity.User;

@Service
public interface UserServiceI {

	User addUser(User user);

}
