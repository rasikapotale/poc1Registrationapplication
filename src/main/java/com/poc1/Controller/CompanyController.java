package com.poc1.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc1.ServiceI.CompanyService;
import com.poc1.model.CompanyDetails;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/company")
@Transactional 
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/save/{emailId}")
	public ResponseEntity<CompanyDetails> addCompany(@RequestBody CompanyDetails details,@PathVariable String emailId ){
		CompanyDetails details2=companyService.addCompanyDetails(details,emailId);
		return new ResponseEntity<CompanyDetails>(details2,HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<CompanyDetails> > getCompanyDetails(@RequestParam ("emailId") String emailId){
		return new ResponseEntity<List<CompanyDetails> >(companyService.getCompanyDetails(emailId),HttpStatus.OK);
	}
	

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCompany(@RequestParam ("emailId") String emailId){
		companyService.deleteCompany(emailId);
		return new ResponseEntity<String>("Deleted Company",HttpStatus.OK);
		
	}
	
//	@DeleteMapping("/delete")
//	public void deleteCompany(@RequestParam ("emailId") String emailId){
//		companyService.deleteCompany(emailId);
//	}
}
