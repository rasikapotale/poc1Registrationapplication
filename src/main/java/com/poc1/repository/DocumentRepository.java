package com.poc1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc1.model.UserDocuments;

@Repository
public interface DocumentRepository extends JpaRepository<UserDocuments, Integer>{

}
