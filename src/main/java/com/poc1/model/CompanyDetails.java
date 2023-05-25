package com.poc1.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Company_Details")
public class CompanyDetails {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		private String companyName;
	    private LocalDate joiningDate;
	    private LocalDate exitDate;
	    private String achievement;
	    private double ctc;
	    
	    @ManyToOne(cascade = CascadeType.PERSIST)
	    private User user;
	    
	
		

}
