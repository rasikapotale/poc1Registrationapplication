package com.poc1.ThirdPartyService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verification")
public class PanAdharVarificationService {
	
	@PostMapping("/verify")
    public VerificationResponse verifyPanAadhar(@RequestBody VerificationRequest request) {
        String panNumber = request.getPanNumber();
        String aadharNumber = request.getAadharNumber();
       
        // Perform verification logic here and return appropriate response
        boolean isVerified = verifyPanAadhar(panNumber, aadharNumber);
        if (isVerified) {
            return new VerificationResponse("verified", "Pan and Aadhar details match");
        } else {
            return new VerificationResponse("not_verified", "Pan and Aadhar details do not match");
        }
    }
    
    // Example verification logic
    private boolean verifyPanAadhar(String panNumber, String aadharNumber) {
        // Perform actual verification logic here
        return true; // For demo purposes, always return true
    }
}
