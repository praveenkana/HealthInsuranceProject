package com.nt.entity;

import java.util.Date;

import jakarta.persistence.Column;
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
@Table(name="DC_EDUCATION")
@AllArgsConstructor
@NoArgsConstructor
public class DCEducationEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer educationId;
	private Integer caseNo;
	@Column(length=30)
	private String highestQualification;
	private Date passOutYear;
}
