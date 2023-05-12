package com.poc1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {
	
	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;
	
	@Bean
	public MongoClient mongiClient() {
		return MongoClients.create(mongoUri);
	}
	
    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "poc1");
    }

}
