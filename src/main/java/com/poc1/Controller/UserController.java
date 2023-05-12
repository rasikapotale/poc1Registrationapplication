package com.poc1.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.poc1.ExceptionClass.PanAdharException;
import com.poc1.ServiceI.UserServiceI;
import com.poc1.ServiceImpl.NotificationService;
import com.poc1.ThirdPartyService.VerificationRequest;
import com.poc1.ThirdPartyService.VerificationResponse;
import com.poc1.model.CompanyDetails;
import com.poc1.model.Email;
import com.poc1.model.User;
import com.poc1.model.UserDocuments;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private static final String UPLOAD_DIR = "C:/PocDocument";
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserServiceI userServiceI;
	
	@Autowired
	private NotificationService notificationService;
	
	@Value("${spring.mail.username}")
	String fromEmail;
    
     @PostMapping("/addUser")
     @ResponseBody
	public User AddUser(@Valid @RequestBody User user) throws PanAdharException
	{
         String url = "http://localhost:9191/verification/verify"; // Replace with actual service URL/
         
         RestTemplate restTemplate = new RestTemplate();
         
         VerificationRequest request = new VerificationRequest();
         request.setPanNumber(user.getPanNo());
         request.setAadharNumber(user.getAdharNo());
         
         VerificationResponse response = restTemplate.postForObject(url, request, VerificationResponse.class);
         
         if(response!=null) {
        	 System.out.println("Verification status: " + response.getStatus());
             System.out.println("Verification message: " + response.getMessage()); 
//             user.setPassword(encoder.encode(user.getPassword()));
      	   User user1= userServiceI.addUser(user);
      	   notificationService.sendEmail(user.getEmailId(), "Add User Details", "User Details Successfully saved");
      	   
      	   Email email=new Email();
      	   email.setSender(fromEmail);
      	   email.setRecipient(user.getEmailId());
      	   email.setSubject("Registration Mail");
      	   email.setBody("User Registration Successfully saved");
      	   System.out.println(email.getSender()+"\n"+email.getRecipient()+"\n"+email.getSubject()+"\n"+email.getBody());
      	           
      		return user1;
         }else {
        	throw new PanAdharException("Pan Card or Adhar Card Not Valid");
         }	
	}
     
     @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     @GetMapping("/welcome")
     public String welcome() {
    	 return "Hello Rasika";
     }
     
     @PreAuthorize("hasAuthority('ROLE_USER')")
     @GetMapping("/getAllUser")
     public List<User> getAllUser()
     {
    	 return userServiceI.getAllUser();
     }
     
     @GetMapping("/search")
     public ResponseEntity<List<User>> searchUsers(@RequestParam ("query") String query){
    	 List<User> list=userServiceI.searchUser(query);
    	 return new ResponseEntity<List<User>>(list,HttpStatus.OK);
     }
     
     @GetMapping("/get")
     public ResponseEntity<User> getUser(@RequestParam ("emailId") String emailId){
    	 User user=userServiceI.getUser(emailId);
    	 return new ResponseEntity<User>(user,HttpStatus.OK);
     }
     
//     @GetMapping("/activeStatus")
////     @RequestParam ("activeStatus") String activeStatus
//     public ResponseEntity<List<User>> getActiveUser(){
//    	 List<User> users=userServiceI.getActiveUsers();
//    	 
//		return new ResponseEntity<List<User>>(users,HttpStatus.FOUND);
//    	 
//     }
     
     @DeleteMapping("/delete/{emailId}")
     public ResponseEntity<String> deleteUser(@PathVariable String emailId) {
         try {
        	 userServiceI.deleteUser(emailId);
             return new ResponseEntity<String>("User delete Successfully",HttpStatus.NO_CONTENT);
         } catch (DataIntegrityViolationException e) {
             return new ResponseEntity<String>("Cannot delete user with associated records", HttpStatus.CONFLICT);
         }
     }
     
 	@PostMapping("/save/{emailId}")
 	public ResponseEntity<User> addCompany(@RequestBody List<CompanyDetails> details,@PathVariable String emailId ){
 		User details2=userServiceI.addCompanyDetails(details,emailId);
 		return new ResponseEntity<User>(details2,HttpStatus.CREATED);
 	}
 	

 	 //This functionality store data into local machine folder
 	  /*@Param
 	   * UPLOAD_DIR-path of directory
 	   */
 	  @PostMapping("/upload")
 	  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile[] files) {
 		  
 	    for (MultipartFile file : files) {
 	      if (!file.isEmpty()) {
 	        String fileName = file.getOriginalFilename();
 	        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

 	        if (!Arrays.asList("pdf", "xls", "xlsx", "jpeg").contains(fileType)) {
 	          return new ResponseEntity<>("Invalid file format.", HttpStatus.BAD_REQUEST);
 	        }
 	        
 	       if (file.getSize() > 2 * 1024 * 1024) {
 	            return new ResponseEntity<>("File size exceeds 2 MB limit", HttpStatus.BAD_REQUEST);
 	        }
 	       
 	        try {
 	          File dir = new File(UPLOAD_DIR);
 	          if (!dir.exists()) {
 	            dir.mkdirs();
 	          }
 	          File uploadedFile = new File(dir.getAbsolutePath() + File.separator + fileName);
 	          file.transferTo(uploadedFile);
 	        } catch (IOException e) {
 	          return new ResponseEntity<>("Failed to upload file.", HttpStatus.INTERNAL_SERVER_ERROR);
 	        }
 	      }
 	    }
 	    return new ResponseEntity<>("Files uploaded successfully.", HttpStatus.OK);
 	  }
 	
 	  
 	  //This functionality store data into database
 	  @PostMapping("/uploadFile")
 	  public ResponseEntity<String> uploadDocuments(@RequestParam("file") MultipartFile[] files,@RequestParam ("emailId") String emailId) {
 		  List<UserDocuments> documents=userServiceI.uploadDocuments(files,emailId);
 		  if(documents!=null) {
 			  System.out.println(documents);
 	 		  return new ResponseEntity<String>("Documents Uploaded Sucessfully",HttpStatus.CREATED);  
 		  }
 		  return new ResponseEntity<String>("Documents Not Uploaded!",HttpStatus.BAD_REQUEST);
 	  }
     
}
