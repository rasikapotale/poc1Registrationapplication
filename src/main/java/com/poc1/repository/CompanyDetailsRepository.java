package com.poc1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc1.model.CompanyDetails;

@Repository
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails, Integer>{

	List<CompanyDetails> findByUserEmailId(String emailId);

	void deleteByUserEmailId(String emailId);

//	 @Query("DELETE FROM poc1.company_details c WHERE c.user_email_id = :emailId")
//	void deleteByEmailId(@Param ("emailId") String emailId);

}
