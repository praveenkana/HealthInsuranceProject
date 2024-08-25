package com.nt.service;

import java.util.List;

import com.nt.bindings.ChildrenInputs;
import com.nt.bindings.DCSummaryReports;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanSelectionInputs;

public interface IDataCollectionMgmtService {

	public Integer generatecaseNo(Integer appid);

	public Integer saveSelectedPlan(PlanSelectionInputs plan);

	public List<String> showAllPlans();

	public Integer saveIncomeDetails(IncomeInputs income);

	public Integer saveEducationDetails(EducationInputs inputs);

	public DCSummaryReports showSummeryReport(Integer caseNo);

	Integer saveChildrenDetails(List<ChildrenInputs> children);





}
