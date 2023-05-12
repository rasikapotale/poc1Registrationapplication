package com.poc1.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
//@Document(collection = "CompanyDetails")
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
	    

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public CompanyDetails() {

		}

		public CompanyDetails(int id, String companyName, LocalDate joiningDate, LocalDate exitDate, String achievement,
				double ctc) {
			this.id = id;
			this.companyName = companyName;
			this.joiningDate = joiningDate;
			this.exitDate = exitDate;
			this.achievement = achievement;
			this.ctc = ctc;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public LocalDate getJoiningDate() {
			return joiningDate;
		}

		public void setJoiningDate(LocalDate joiningDate) {
			this.joiningDate = joiningDate;
		}

		public LocalDate getExitDate() {
			return exitDate;
		}

		public void setExitDate(LocalDate exitDate) {
			this.exitDate = exitDate;
		}

		public String getAchievement() {
			return achievement;
		}

		public void setAchievement(String achievement) {
			this.achievement = achievement;
		}

		public double getCtc() {
			return ctc;
		}

		public void setCtc(double ctc) {
			this.ctc = ctc;
		}
	
		

}
