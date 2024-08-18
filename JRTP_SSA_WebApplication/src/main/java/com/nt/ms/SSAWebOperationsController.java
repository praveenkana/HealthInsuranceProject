package com.nt.ms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ssa-web-api")
public class SSAWebOperationsController {
	@GetMapping("/getssn/{ssn}")
	public ResponseEntity<String> getStateName(@PathVariable Integer ssn){
		
		
		if(String.valueOf(ssn).length()!=9){
			return new ResponseEntity<String>("invalid ssn",HttpStatus.BAD_REQUEST);
		}
		int  stateCode=ssn%100;
		String stateName=null;
		if(stateCode==01)
			stateName="WashingtonDC";
		else if(stateCode==02)
			stateName="Ohio";
		else if(stateCode==03)
			stateName="Texas";
		else if(stateCode==04)
			stateName="california";
		else if(stateCode==05)
			stateName="newyork";
		else
			stateName="invalid ssn";
		
		return new ResponseEntity<String>(stateName,HttpStatus.OK);
		
	}

}
