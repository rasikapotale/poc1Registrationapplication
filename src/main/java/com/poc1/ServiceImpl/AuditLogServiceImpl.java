package com.poc1.ServiceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.poc1.model.AuditLog;
import com.poc1.repository.AuditLogRepository;

@Service
public class AuditLogServiceImpl {

	@Autowired
	private AuditLogRepository auditLogRepository;
	
	public List<AuditLog> getAllJobs() {
		List<AuditLog> logs=auditLogRepository.findAll();		
			return logs;
		
	}

	public List<AuditLog> fetchAuditLogs(String status) {
		List<AuditLog> auditLogs=auditLogRepository.findByStatus(status);	
		return auditLogs;
	}

	public Page<AuditLog> findAuditLog(int page, int size) {
		Page<AuditLog> logs=auditLogRepository.findAll(PageRequest.of(page, size));
		return logs;
	}

}
