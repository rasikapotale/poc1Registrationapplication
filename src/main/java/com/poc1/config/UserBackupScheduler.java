package com.poc1.config;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc1.model.BackupTask;
import com.poc1.repository.UserRepository;

@Component
@EnableScheduling
public class UserBackupScheduler{

	
	@Autowired
	private BackupTask backupTask;
	
	
	@Scheduled(cron = "0 1 17 * * *")
    public void backupData() {
    	backupTask.backupData();
    }
	
	@Scheduled(cron = "0 7 18 * * *")
	public void inactiveData() {
		backupTask.inactiveUserData();
		
	}
//	@Autowired
//	CompanyBackupRepo companyBackupRepository;
	
//	@Override
//	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//		taskRegistrar.addTriggerTask(() -> backupUsers(), triggerContext -> {
//		      Calendar nextExecutionTime = new GregorianCalendar();
//		      Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
//		      nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
//		      nextExecutionTime.add(Calendar.DAY_OF_MONTH, 1);
//		      return nextExecutionTime.getTime().toInstant();
//		    });	
//	}
//	  private void backupUsers() {
	
//			List<User> users=userRepository.findByActiveStatus("Yes");
//			System.out.println("In backup service :"+users);
//			for(User user:list) {
//				mongoTemplate.save(user,"User_Details");
//			}
//		    List<User> user = userRepository.findAll();
//		    userBackupRepository.saveAll(user);
		  
//		  List<User> data=userRepository.findByActiveStatus("Yes");
//			System.out.println("Bakcup Data:"+data);
//			mongoTemplate.insertAll(data);
//		  }

}
