package com.poc1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc1.Entity.User;
import com.poc1.Entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String>{

	UserRole findByEmail(String username);

}
