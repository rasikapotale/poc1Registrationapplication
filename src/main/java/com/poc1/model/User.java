package com.poc1.model;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Document(collection = "UserDetails")
@Table(name="User_Details")
public class User {

	@Id
	@Email
	@NotNull
	@NotEmpty
	private String emailId;
	
	@NotNull
	@Size(min = 2, max = 10, message = "password must be min of 3 char and max 10 char !!")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "Minimum eight characters, at least one letter and one number and one special character",flags={Flag.CASE_INSENSITIVE})
	private String password;
	
	@NotEmpty
	@Size(min=1, message = "enter your full name")
	private String fullName;
	
	@NotEmpty(message = "enter your country ")
	private String country;
	
	@NotEmpty(message = "enter your state")
	private String state;
	
	@NotEmpty(message = "enter your district")
	private String district;
	
	@NotEmpty(message = "enter your pincode")
	private String pincode;
	
	@NotEmpty

	@Length(min=12,max=12, message = "please enter 12 digit adhar number")
	private String adharNo;
	
	@NotEmpty
	@Length(min=10,max=10, message = "please enter Valid Pan number")
	private String panNo;
	
	@NotEmpty(message = "please enter city")
	private String city;
	
	@NotNull
	@Pattern(regexp = "(0/91)?[7-9][0-9]{9}")
	@Length(message = "please enter 10 digit adhar number")
	private String mobilenumber;
	
	@NotEmpty
	private String userType;
	
	@NotEmpty
	private String activeStatus;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	public List<CompanyDetails> companyDetails;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	public List<UserDocuments>  userDocuments;
	
	
	public List<UserDocuments> getUserDocuments() {
		return userDocuments;
	}

	public void setUserDocuments(List<UserDocuments> userDocuments) {
		this.userDocuments = userDocuments;
	}

	public List<CompanyDetails> getCompanyDetails() {
		return companyDetails;
	}
	
	public void setCompanyDetails(List<CompanyDetails> companyDetails) {
		this.companyDetails = companyDetails;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getAdharNo() {
		return adharNo;
	}
	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	public User(String emailId,String password,String fullName, String country, String state, String district,String pincode,
			String adharNo, String panNo,String city, String mobilenumber, String userType, String activeStatus) {

		this.emailId = emailId;
		this.password = password;
		this.fullName = fullName;
		this.country = country;
		this.state = state;
		this.district = district;
		this.pincode = pincode;
		this.adharNo = adharNo;
		this.panNo = panNo;
		this.city = city;
		this.mobilenumber = mobilenumber;
		this.userType = userType;
		this.activeStatus = activeStatus;
	}
	public User() {

	}

}
