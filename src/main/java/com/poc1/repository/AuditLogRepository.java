package com.poc1.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.poc1.model.AuditLog;

@Repository
public interface AuditLogRepository extends MongoRepository<AuditLog, String>{

	List<AuditLog> findByStatus(String status);


}
