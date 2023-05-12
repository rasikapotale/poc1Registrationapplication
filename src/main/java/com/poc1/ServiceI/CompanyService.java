package com.poc1.ServiceI;

import java.util.List;

import com.poc1.model.CompanyDetails;

public interface CompanyService {

	public CompanyDetails addCompanyDetails(CompanyDetails details,String emailId);

	public List<CompanyDetails>  getCompanyDetails(String emailId);

	public String deleteCompany(String emailId);

}
