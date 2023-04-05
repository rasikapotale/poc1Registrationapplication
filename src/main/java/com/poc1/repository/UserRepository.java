package com.poc1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc1.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
