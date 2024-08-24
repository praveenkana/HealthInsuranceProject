package com.nt.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "PLAN_ENTITY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer planId;
	private String planName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String activeSw;
	private String Description;
	private String createdBy;
	private String updatedBy;
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdOn;
	@Column(insertable = false)
	@UpdateTimestamp
	private LocalDateTime updatedOn;

}
