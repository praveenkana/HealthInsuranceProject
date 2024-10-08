package com.nt.bindings;

import java.util.List;
import lombok.Data;

@Data
public class DCSummaryReports {

	private List<ChildrenInputs> childrens;
	private EducationInputs educationdetails;
	private IncomeInputs incomeDetails;
	private CitizenAppRegistrationInputs AppDetails;
	private String planName;

}
