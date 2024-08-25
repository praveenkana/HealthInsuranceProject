package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.DCChildrenEntity;

public interface IDCChildrenRepository extends JpaRepository<DCChildrenEntity, Integer> {

	public List<DCChildrenEntity> findByCaseNo(Integer caseNo);

}
