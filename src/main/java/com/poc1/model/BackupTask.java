package com.poc1.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.First;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators.Eq;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.poc1.repository.UserRepository;
@Service
public class BackupTask {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;
	
	
	private Date calculateNextJobDate() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date()); // Set the current date and time
	    
	    // Set the time to 12:30 AM
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 30);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    
	    // Set the day to the first day of the next month
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    calendar.add(Calendar.MONTH, 1);
	    
	    return calendar.getTime();
	}

	
	public void activeUserBackupData() {
		
		List<User> data=userRepository.findByActiveStatus("Yes");
//		    
//		    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
//		    MongoDatabase database = mongoClient.getDatabase("poc1");
//
//	        // Create a codec registry with the "User" class registered
//	        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
//	                MongoClientSettings.getDefaultCodecRegistry(),
//	                CodecRegistries.fromProviders(PojoCodecProvider.builder().register(User.class,CompanyDetails.class).build())
//	        );
//
//	        // Get a handle to the "users" collection
//	        MongoCollection<User> collection = database.getCollection("ActiveUser", User.class)
//	                .withCodecRegistry(codecRegistry);
//	        System.out.println("Active user from mongoDB: "+collection);
//	        // Query the "users" collection
//	        List<User> users = collection.find().into(new ArrayList<>());
//	        System.out.println("User from mongo: "+users);
//	        for(User user:users) {
//	        	System.out.println(user.getEmailId());
	        // Create audit log entry
		
	        AuditLog auditLog = new AuditLog();
	        auditLog.setJobName("Active Trace");
	        auditLog.setJobDateTime(new Date());
	        auditLog.setStatus("Done");
	        auditLog.setNextJobDate(calculateNextJobDate());
	        auditLog.setBackupCount(data.size());
	        // Insert audit log into MongoDB
	        mongoTemplate.insert(auditLog);
	        for(User user1:data) {
//	        		if(!user1.getEmailId().equals(user.getEmailId())) {
	        			mongoTemplate.save(user1, "ActiveUser");
	        		}
//	        	}
	        	
//	        }

	}	
	
	@Transactional
	public void inactiveUserBackupData() {
		List<User> data=userRepository.findByActiveStatus("No");
		if(data!=null) {
        // Create audit log entry
        AuditLog auditLog = new AuditLog();
        auditLog.setJobName("Inactive Trace");
        auditLog.setJobDateTime(new Date());
        auditLog.setStatus("Done");
        auditLog.setNextJobDate(calculateNextJobDate());
        auditLog.setBackupCount(data.size());
        // Insert audit log into MongoDB
        mongoTemplate.insert(auditLog);
		for(User user:data) {
			mongoTemplate.save(user,"InactiveUser");
			userRepository.deleteByActiveStatus("No");
		}
		}
	}
	
    
//    private Date calculateNextJobDate() {
        // Calculate and return the next job date based on your requirements
//    }
	
}
