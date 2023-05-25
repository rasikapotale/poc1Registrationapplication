package com.poc1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc1.ServiceImpl.AuditLogServiceImpl;
import com.poc1.model.AuditLog;
import com.poc1.repository.AuditLogRepository;

@RestController
@RequestMapping("/audit-log")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AuditLogController {
	
	@Autowired
	private AuditLogServiceImpl auditLogService;
	
	@Autowired
	private AuditLogRepository auditLogRepository;
	

	@GetMapping("/get")
	public ResponseEntity<List<AuditLog>> getAllSchedulingJobs(){
		List<AuditLog> jobs=auditLogService.getAllJobs();
		if(jobs!=null) {
			return new ResponseEntity<List<AuditLog>>(jobs,HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	

	@GetMapping("/status")
	public ResponseEntity<List<AuditLog>> getJobStatus(@RequestParam ("status") String status){
		List<AuditLog> auditLogs=auditLogService.fetchAuditLogs(status);
		if(auditLogs==null) {
			
		}
		if(auditLogs!=null) {
			return new ResponseEntity<List<AuditLog>>(auditLogs,HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}

	//get data by paggination--page-page number and size-No of object data print
	@GetMapping("/allScheduleJobs/{page}/{size}")
	public ResponseEntity<Page<AuditLog>> getAllScheduleJobs(@PathVariable int page,@PathVariable int size){
		Page<AuditLog> auditLogs=auditLogService.findAuditLog(page,size);
		return new ResponseEntity<Page<AuditLog>>(auditLogs,HttpStatus.FOUND);
	}
//	public Page<AuditLog> getAll(Pageable pageable){
//		return auditLogRepository.findAll(pageable);
//	}
	
}
