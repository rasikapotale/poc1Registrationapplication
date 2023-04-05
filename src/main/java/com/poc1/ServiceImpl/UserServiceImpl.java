package com.poc1.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc1.Entity.User;
import com.poc1.ServiceI.UserServiceI;
import com.poc1.repository.UserRepository;


public class UserServiceImpl implements UserServiceI{

	@Autowired
	UserRepository userRepository;
	@Override
	public User addUser(User user) {
		
		return userRepository.save(user);
	}

}
