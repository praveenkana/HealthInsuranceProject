package com.nt.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenAppRegistrationInputs {
	
	private String fullName;
	private String email;
	private String gender;
	private Long ssn;
	private Long phoneNo;
	private LocalDate dob;

}
