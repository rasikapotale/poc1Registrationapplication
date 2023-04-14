package com.poc1.ServiceI;

import java.util.List;

import com.poc1.Entity.User;


public interface UserServiceI {

	public User addUser(User user);

	public List<User> getAllUser();

	public List<User> searchAllData();

}
