package com.poc1.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "audit_log")
public class AuditLog {
    @Id
    private String id;
    private String jobName;
    private Date jobDateTime;
    private String status;
    private Date nextJobDate;
    private int backupCount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Date getJobDateTime() {
		return jobDateTime;
	}
	public void setJobDateTime(Date jobDateTime) {
		this.jobDateTime = jobDateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getNextJobDate() {
		return nextJobDate;
	}
	public void setNextJobDate(Date nextJobDate) {
		this.nextJobDate = nextJobDate;
	}
	public int getBackupCount() {
		return backupCount;
	}
	public void setBackupCount(int backupCount) {
		this.backupCount = backupCount;
	}
	public AuditLog(String id, String jobName, Date jobDateTime, String status, Date nextJobDate, int backupCount) {

		this.id = id;
		this.jobName = jobName;
		this.jobDateTime = jobDateTime;
		this.status = status;
		this.nextJobDate = nextJobDate;
		this.backupCount = backupCount;
	}
	public AuditLog() {
		
	}
    
    
}
