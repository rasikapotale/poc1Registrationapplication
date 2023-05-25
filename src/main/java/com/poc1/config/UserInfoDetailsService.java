package com.poc1.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

<<<<<<< HEAD:src/main/java/com/poc1/config/UserInfoDetailsService.java
import com.poc1.model.UserRole;
=======
import com.poc1.Entity.UserInfoDetails;
import com.poc1.Entity.UserRole;
>>>>>>> cf7145f0dabce11b8351b1876f6542778878ce5b:src/main/java/com/poc1/ServiceImpl/UserInfoDetailsService.java
import com.poc1.repository.UserRoleRepository;

@Service
public class UserInfoDetailsService  implements UserDetailsService{

	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserRole> user= userRoleRepository.findByEmail(username);
		return user.map(UserInfoDetails::new)
				.orElseThrow(()->new UsernameNotFoundException("User not found"+username));
	}
}
