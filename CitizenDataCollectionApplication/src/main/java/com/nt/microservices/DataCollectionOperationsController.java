package com.nt.microservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.ChildrenInputs;
import com.nt.bindings.DCSummaryReports;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanSelectionInputs;
import com.nt.service.IDataCollectionMgmtService;

@RestController
@RequestMapping("dc-api ")
public class DataCollectionOperationsController {
	@Autowired
	private IDataCollectionMgmtService service;

	@PostMapping("/generatecaseno/{appid}")
	public ResponseEntity<Integer> generateCaseNumber(@PathVariable Integer appid) {

		Integer caseNo = service.generatecaseNo(appid);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.OK);

	}

	@GetMapping("/plannames")
	public ResponseEntity<List<String>> showAllPlans() {
		List<String> plans = service.showAllPlans();
		return new ResponseEntity<List<String>>(plans, HttpStatus.OK);
	}

	@PutMapping("/updateSelectedPlan")
	public ResponseEntity<Integer> savePlanselection(@RequestBody PlanSelectionInputs inputs) {

		Integer caseNo = service.saveSelectedPlan(inputs);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.OK);

	}

	@PostMapping("/saveIncome")
	public ResponseEntity<Integer> saveCitizenIncome(@RequestBody IncomeInputs income) {

		Integer caseNo = service.saveIncomeDetails(income);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.CREATED);

	}

	@PostMapping("/saveEducation")
	public ResponseEntity<Integer> saveEducationDetails(@RequestBody EducationInputs education) {
		Integer caseNo = service.saveEducationDetails(education);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.CREATED);

	}

	@PostMapping("/saveChild")
	public ResponseEntity<Integer> saveChildrenDetails(@RequestBody List<ChildrenInputs> children) {

		Integer caseNo = service.saveChildrenDetails(children);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.CREATED);

	}

	@GetMapping("/report")
	public ResponseEntity<DCSummaryReports> showReports(@PathVariable Integer caseNo) {

		DCSummaryReports reports = service.showSummeryReport(caseNo);
		return new ResponseEntity<DCSummaryReports>(reports, HttpStatus.OK);

	}
}
