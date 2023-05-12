package com.poc1.ServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.poc1.ServiceI.UserServiceI;
import com.poc1.ThirdPartyService.VerificationRequest;
import com.poc1.ThirdPartyService.VerificationResponse;
import com.poc1.model.CompanyDetails;
import com.poc1.model.User;
import com.poc1.model.UserDocuments;
import com.poc1.repository.CompanyDetailsRepository;
import com.poc1.repository.DocumentRepository;
import com.poc1.repository.UserRepository;

@Service
public class UserServiceImpl implements UserServiceI{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private DocumentRepository documentRepository;
	
	
	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
      return userRepository.findAll();
	}

	@Override
	public List<User> searchUser(String query) {
		List<User> list=userRepository.findByEmailIdLikeIgnoreCaseOrFullNameLikeIgnoreCaseOrCountryLikeIgnoreCaseOrStateLikeIgnoreCaseOrAdharNoLikeIgnoreCaseOrPanNoLikeIgnoreCaseOrMobilenumberLikeIgnoreCase(query,query,query,query,query,query,query);

			if(list!=null) {
				return list;
			}else {
				throw new NullPointerException();
//				return null ;
			}

	}

	@Transactional
	@Override
	public User getUser(String emailId) {
		User user=userRepository.findByEmailId(emailId);
		
		if(user!=null) {
			return user;
		}
		else {
			throw new NullPointerException();
		}
	}

	@Override
	    @Transactional
	    public void deleteUser(String emailId) throws DataIntegrityViolationException {
	        userRepository.deleteById(emailId);
	    }


	@Override
	public User addCompanyDetails(List<CompanyDetails> details,String emailId) {
		User userData=userRepository.findByEmailId(emailId);
		System.out.println(userData);
		if(userData!=null) {
			userData.setCompanyDetails(details);
			
            return userRepository.save(userData);
		}else {
            throw new UsernameNotFoundException("User with id " + emailId + " not found");
        }
	}

	@Override
	public List<UserDocuments> uploadDocuments(MultipartFile[] files, String emailId) {
        List<UserDocuments> uploadedFiles = new ArrayList<>();
        User userData=userRepository.findByEmailId(emailId);
        if(userData!=null) {
            for (MultipartFile file : files) {
                if (file.getContentType().equals("application/pdf")
                        || file.getContentType().equals("application/vnd.ms-excel")
                        || file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                        || file.getContentType().equals("image/jpeg")) {
                    if (file.getSize() <= 2 * 1024 * 1024) {
                        try {
                        	UserDocuments fileEntity = new UserDocuments();
                        	fileEntity.setFilename(StringUtils.cleanPath(file.getOriginalFilename()));
    						fileEntity.setFileData(file.getBytes());
    						fileEntity.setUser(userData);
    						 documentRepository.save(fileEntity);
    		                    uploadedFiles.add(fileEntity);
    					} catch (IOException e) {
    						e.printStackTrace();
    					}
                       
                    }
                }
            }
        }else {
        	System.out.println("Enter Valid User");
        	throw new UsernameNotFoundException("User with id " + emailId + " not found");
        }
        return uploadedFiles;
    }
	

//	@Override
//	public List<User> getActiveUsers() {
//		List<User> users=userRepository.findByActiveStatus("yes");
//		return users;
//	}

//	@Override
//	public CompanyDetails addCompanyDetails(CompanyDetails companyDetails, String emailId) {
//		User UserData=userRepository.findByEmailId(emailId);
//		if(UserData!=null) {
////			UserData.setCompanyDetails(user.getCompanyDetails());
////			UserData.setCompanyDetails(companyDetails);
//			return detailsRepository.save(companyDetails);
//		}
//		return null;
//	}

}
