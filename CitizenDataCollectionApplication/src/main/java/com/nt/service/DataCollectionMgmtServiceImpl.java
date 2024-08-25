package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bindings.ChildrenInputs;
import com.nt.bindings.CitizenAppRegistrationInputs;
import com.nt.bindings.DCSummaryReports;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanSelectionInputs;
import com.nt.entity.CitizenAppRegistrationEntity;
import com.nt.entity.DCCaseEntity;
import com.nt.entity.DCChildrenEntity;
import com.nt.entity.DCEducationEntity;
import com.nt.entity.DCIncomeEntity;
import com.nt.entity.PlanEntity;
import com.nt.repository.ICitizenAppRegistrationRepository;
import com.nt.repository.IDCCaseRepository;
import com.nt.repository.IDCChildrenRepository;
import com.nt.repository.IDCEducationRepository;
import com.nt.repository.IDCIncomeRepository;
import com.nt.repository.IDCPlanMasterRepository;

@Service
public class DataCollectionMgmtServiceImpl implements IDataCollectionMgmtService {
	@Autowired
	private IDCCaseRepository caserepo;
	@Autowired
	private IDCPlanMasterRepository planrepo;
	@Autowired
	private IDCIncomeRepository incomerepo;
	@Autowired
	private IDCEducationRepository educationrepo;
	@Autowired
	private IDCChildrenRepository childrepo;
	@Autowired
	private
	ICitizenAppRegistrationRepository apprepo;

	@Override
	public Integer generatecaseNo(Integer appid) {
		Optional<DCCaseEntity> id = caserepo.findById(appid);
		if (id.isPresent()) {
			DCCaseEntity entity = new DCCaseEntity();
			entity.setAppId(appid);
			return caserepo.save(entity).getCaseNo();
		}
		return 0;
	}

	@Override
	public Integer saveSelectedPlan(PlanSelectionInputs plan) {

		Optional<DCCaseEntity> opt = caserepo.findById(plan.getCaseNo());
		if (opt.isPresent()) {
			DCCaseEntity entity = opt.get();
			entity.setPlanId(plan.getPlanId());
			caserepo.save(entity);
			return entity.getCaseNo();
		}
		return 0;
	}

	@Override
	public List<String> showAllPlans() {

		List<PlanEntity> list = planrepo.findAll();
		/*
		 * for(PlanEntity entity:list) { entity.getPlanName().toString(); }
		 */

		List<String> planNameList = list.stream().map(plan -> plan.getPlanName()).toList();
		return planNameList;
	}

	@Override
	public Integer saveIncomeDetails(IncomeInputs income) {

		Optional<DCIncomeEntity> incomeentity = incomerepo.findById(income.getCaseNo());
		DCIncomeEntity entity = new DCIncomeEntity();
		BeanUtils.copyProperties(incomeentity, entity);
		incomerepo.save(entity);

		return entity.getCaseNo();
	}

	@Override
	public Integer saveEducationDetails(EducationInputs inputs) {

		Optional<DCEducationEntity> opt = educationrepo.findById(inputs.getCaseNo());
		if (opt.isPresent()) {
			DCEducationEntity entity = new DCEducationEntity();
			BeanUtils.copyProperties(inputs, entity);
			educationrepo.save(entity);
			return entity.getCaseNo();
		}
		return 0;
	}

	@Override
	public Integer saveChildrenDetails(List<ChildrenInputs> children) {

		// convert each binding class object to each entity class object
		children.forEach(child -> {
			DCChildrenEntity entity = new DCChildrenEntity();
			BeanUtils.copyProperties(children, entity);
			childrepo.save(entity);
		});

		return children.get(0).getCaseNo();
	}

	@Override
	public DCSummaryReports showSummeryReport(Integer caseNo) {

		// get multiple entities based on case no

		List<DCChildrenEntity> childlist = childrepo.findByCaseNo(caseNo);
		DCIncomeEntity incomeEntity = incomerepo.findByCaseNo(caseNo);
		DCEducationEntity educationEntity = educationrepo.findByCaseNo(caseNo);
		Optional<DCCaseEntity> opt = caserepo.findById(caseNo);
		String planName = null;
		Integer appid = null;
		if (opt.isPresent()) {
			DCCaseEntity caseEntity = new DCCaseEntity();
			Integer planId = caseEntity.getPlanId();
			appid = caseEntity.getAppId();
			Optional<PlanEntity> pid = planrepo.findById(planId);
			if (pid.isPresent()) {
				planName = pid.get().getPlanName();
			}

		}
		Optional<CitizenAppRegistrationEntity> optId = apprepo.findById(appid);
		CitizenAppRegistrationEntity appregEntity=null;
		if(optId.isPresent()) {
			appregEntity=optId.get();
		}

		
		
		//convert entity objects to binding objects
	
		EducationInputs education=new EducationInputs();
		BeanUtils.copyProperties(educationEntity, education);
		IncomeInputs income=new IncomeInputs();
		BeanUtils.copyProperties(incomeEntity, income);
		List<ChildrenInputs> list=new ArrayList();
		
		childlist.forEach(child->{
			ChildrenInputs inputs=new ChildrenInputs();
			BeanUtils.copyProperties(inputs, list);
			list.add(inputs);
			
		});
		
		CitizenAppRegistrationInputs appinputs=new CitizenAppRegistrationInputs();
		BeanUtils.copyProperties(appregEntity, appinputs);
		//prepare dcsummaryreport
		
		DCSummaryReports report=new DCSummaryReports();
		report.setAppDetails(appinputs);
		report.setChildrens(list);
		report.setEducationdetails(education);
		report.setIncomeDetails(income);
		report.setPlanName(planName);
		return report;
	}



	

	

}
