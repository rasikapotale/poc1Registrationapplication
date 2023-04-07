package com.poc1.Entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
@Id
@Email
	private String emailId;
@NotNull
@Size(min=2, max=10, message = "password must be min of 3 char and max 10 char !!")
//@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
	private String password;
//@NotEmpty
//@Size(min=4, message = "enter your full name")
	private String fullName;
//@NotEmpty(message = "enter your country ")
	private String country;
//@NotEmpty(message = "enter your country")
	private String state;
//@NotEmpty(message = "enter your state")
	private String district;
//@NotEmpty(message = "enter your district")
	private int pincode;
//@NotEmpty
//@Length(max=12, message = "please enter 12 digit adhar number")
	private Long adharNo;
//@NotEmpty
//@Length(max=10, message = "please enter 10 digit adhar number")
	private String panNo;
//@NotEmpty(message = "please enter city")
	private String city;
//@NotNull
//@Length(max=10, message = "please enter 10 digit adhar number")
	private Long mobilenumber; 
//@NotEmpty
	private String userType;
//@NotEmpty
	private String activeStatus;
	
}
