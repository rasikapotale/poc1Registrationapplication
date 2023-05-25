package com.poc1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc1.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>{

	List<User> findByEmailIdLikeIgnoreCaseOrFullNameLikeIgnoreCaseOrCountryLikeIgnoreCaseOrStateLikeIgnoreCaseOrAdharNoLikeIgnoreCaseOrPanNoLikeIgnoreCaseOrMobilenumberLikeIgnoreCase
	(String emailId, String fullName,String country,String state,String adharNo,String panNo,String mobilenumber);

	User findByEmailId(String emailId);

//	 @Query("SELECT * from user_details where user_details.active_status=:activeStatus")	
//	 @Query("Select u from User u WHERE u.activeStatus=:activeStatus")
//	List<User> findActiveUsers(@Param ("activeStatus") String activeStatus);

//	 @Query("Select u from User u WHERE u.activeStatus=:Yes")
//	List<User> findActiveUsers(String activeStatus);

	List<User> findByActiveStatus(String activeStatus);

	void deleteByActiveStatus(String string);

	
//	List<User> findByFullNameLikeOrEmailIdLikeOrCountryLike();

	

}
