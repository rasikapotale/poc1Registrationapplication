package com.poc1.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poc1.Entity.UserInfoDetails;
import com.poc1.Entity.UserRole;
import com.poc1.repository.UserRoleRepository;

@Service
public class UserInfoDetailsService  implements UserDetailsService{

	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserRole user= userRoleRepository.findByEmail(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("no user");
		}
		return  new UserInfoDetails(user);
	}

}
