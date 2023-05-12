package com.poc1.model;

import java.util.ArrayList;
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
	
	public void backupData() {
		
		List<User> data=userRepository.findByActiveStatus("Yes");
//		 MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoUri)); // create MongoDB client
//		    MongoDatabase database = mongoClient.getDatabase("poc1"); // get database
//		    MongoCollection<Document> collection = database.getCollection("ActiveUser");
		    
		    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		    MongoDatabase database = mongoClient.getDatabase("poc1");
//		    MongoCollection<User> usersCollection = database.getCollection("ActiveUser", User.class);
//		    FindIterable<User> users=usersCollection.find();


	        // Create a codec registry with the "User" class registered
	        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
	                MongoClientSettings.getDefaultCodecRegistry(),
	                CodecRegistries.fromProviders(PojoCodecProvider.builder().register(User.class,CompanyDetails.class).build())
	        );

	        // Get a handle to the "users" collection
	        MongoCollection<User> collection = database.getCollection("ActiveUser", User.class)
	                .withCodecRegistry(codecRegistry);

	        // Query the "users" collection
	        List<User> users = collection.find().into(new ArrayList<>());
	        System.out.println("User from mongo: "+users);
	        for(User user:users) {
	        	System.out.println(user.getEmailId());
	        for(User user1:data) {
	        		if(!user1.getEmailId().equals(user.getEmailId())) {
	        			mongoTemplate.save(user1, "ActiveUser");
	        		}
	        	}
	        	
	        }
	        
//		for(User user:data) {
//			for(User u:users) {
//				ObjectId userId = new ObjectId(u.getEmailId());
//				System.out.println("User Id of mongo"+userId);
////		        Bson filter = Filters.eq("_id", userId);
//				Document filter = (Document) Filters.eq("emailId", user.getEmailId());
//				System.out.println("Filter"+filter);
////				if(userId=user.getEmailId()) {
//				
//					mongoTemplate.save(user,"ActiveUser");
////				}
//			}		
//		}
//		mongoTemplate.insertAll(data);
	}	
	
	@Transactional
	public void inactiveUserData() {
		List<User> data=userRepository.findByActiveStatus("No");
		for(User user:data) {
			mongoTemplate.save(user,"InactiveUser");
			userRepository.deleteByActiveStatus("No");
		}
	}
	
}
