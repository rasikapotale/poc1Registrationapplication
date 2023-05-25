package com.poc1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.poc1.model.BackupTask;


@Component
@EnableScheduling
public class UserBackupScheduler{

	
	@Autowired
	private BackupTask backupTask;
	
	@Scheduled(cron = "0 0 1 * * *")
    public void backupData() {
    	backupTask.activeUserBackupData();;
    }
	
	@Scheduled(cron = "0 0 1 * * *")
	public void inactiveData() {
		backupTask.inactiveUserBackupData();
		
	}


}
