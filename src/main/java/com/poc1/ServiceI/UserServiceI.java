package com.poc1.ServiceI;

import java.util.List;

<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;

import com.poc1.model.CompanyDetails;
import com.poc1.model.User;
import com.poc1.model.UserDocuments;

=======
import com.poc1.Entity.User;
>>>>>>> cf7145f0dabce11b8351b1876f6542778878ce5b


public interface UserServiceI {

	public User addUser(User user);

	public List<User> getAllUser();

<<<<<<< HEAD
	public List<User> searchUser(String query);

	public User getUser(String emailId);


	public void deleteUser(String emailId);

	public User addCompanyDetails(List<CompanyDetails> details, String emailId);

	public List<UserDocuments> uploadDocuments(MultipartFile[] files, String emailId);

//	public User addCompanyDetails(List<CompanyDetails> details, String emailId);

//	public List<User> getActiveUsers();

//	public List<User> getActiveUsers(String activeStatus);

//	public CompanyDetails addCompanyDetails(CompanyDetails companyDetails, String emailId);
=======
	public List<User> searchAllData();
>>>>>>> cf7145f0dabce11b8351b1876f6542778878ce5b

}
