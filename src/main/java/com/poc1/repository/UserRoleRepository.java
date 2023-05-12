package com.poc1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc1.model.UserRole;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String>{

	Optional<UserRole> findByEmail(String username);



}
