package com.nt.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="DC_CHILDREN")
@AllArgsConstructor
@NoArgsConstructor
public class DCChildrenEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer childId;
	private Integer caseNo;
	private LocalDate childDOB;
	private Long childSSN;
	
	

}
