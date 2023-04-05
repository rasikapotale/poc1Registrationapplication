package com.poc1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
@Id
	private String emailId;
	private String password;
	private String fullName;
	private String country;
	private String state;
	private String district;
	private int pincode;
	private Long adharNo;
	private String panNo;
	private String city;
	private Long mobilenumber; 
	private String userType;
	private String activeStatus;
	
}
