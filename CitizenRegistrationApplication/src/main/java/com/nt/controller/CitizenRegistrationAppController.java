package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.CitizenAppRegistrationInputs;
import com.nt.service.ICitizenAppRegistrationService;

@RestController
@RequestMapping("/register-citizen-api")
public class CitizenRegistrationAppController {
	@Autowired
	private  ICitizenAppRegistrationService service;
	
	@PostMapping("/save")
	public ResponseEntity<String> registerCitizenApplication(@RequestBody CitizenAppRegistrationInputs inputs)throws Exception{
	/*	
		try {
			int appid = service.registerCitizenApplication(inputs);
		 	if(appid>0) 
				return new ResponseEntity<String>("citizen is registered with app id :: "+appid,HttpStatus.CREATED);
		 	else 
				return new ResponseEntity<String>("invalid ssn or citizen not belongs to california",HttpStatus.OK);
		 	
		}catch(Exception e) {
			
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			
		}*/
		int appid=service.registerCitizenApplication(inputs);
		
		return new ResponseEntity<String>("citizen registered with id ::"+appid,HttpStatus.CREATED);
	}

}
