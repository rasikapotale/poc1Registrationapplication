package com.poc1.ServiceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poc1.ServiceI.CompanyService;
import com.poc1.model.CompanyDetails;
import com.poc1.model.User;
import com.poc1.repository.CompanyDetailsRepository;
import com.poc1.repository.UserRepository;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyDetailsRepository companyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public CompanyDetails addCompanyDetails(CompanyDetails details,String emailId) {
		User userData=userRepository.findByEmailId(emailId);
		System.out.println(userData);
		if(userData!=null) {
			details.setUser(userData);
            return companyRepository.save(details);
		}else {
            throw new UsernameNotFoundException("User with id " + emailId + " not found");
        }
	}

	@Override
	public List<CompanyDetails> getCompanyDetails(String emailId) {
		List<CompanyDetails> list=companyRepository.findByUserEmailId(emailId); 
		return list;
	}

	@Override
	public String deleteCompany(String emailId) {	
		if(emailId!=null) {
			companyRepository.deleteByUserEmailId(emailId);
//			companyRepository.deleteByEmailId(emailId);
			return "Company Deleted";
		}else {
            throw new UsernameNotFoundException("User with id " + emailId + " not found");

		}
	}

}
