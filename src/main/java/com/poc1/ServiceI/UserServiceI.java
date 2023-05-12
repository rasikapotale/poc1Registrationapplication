package com.poc1.ServiceI;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.poc1.model.CompanyDetails;
import com.poc1.model.User;
import com.poc1.model.UserDocuments;



public interface UserServiceI {

	public User addUser(User user);

	public List<User> getAllUser();

	public List<User> searchUser(String query);

	public User getUser(String emailId);


	public void deleteUser(String emailId);

	public User addCompanyDetails(List<CompanyDetails> details, String emailId);

	public List<UserDocuments> uploadDocuments(MultipartFile[] files, String emailId);

//	public User addCompanyDetails(List<CompanyDetails> details, String emailId);

//	public List<User> getActiveUsers();

//	public List<User> getActiveUsers(String activeStatus);

//	public CompanyDetails addCompanyDetails(CompanyDetails companyDetails, String emailId);

}
