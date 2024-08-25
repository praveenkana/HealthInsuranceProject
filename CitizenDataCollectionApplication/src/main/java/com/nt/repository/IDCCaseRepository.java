package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.DCCaseEntity;

public interface IDCCaseRepository extends JpaRepository<DCCaseEntity, Integer> {

	public DCCaseEntity findByCaseNo(Integer caseNo);

}
