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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CITIZENDETAILS_FOR_APP_REGISTRATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenAppRegistrationEntity {

	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "regIdSequence", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer appId;
	@Column(length = 30)
	private String fullName;
	@Column(length = 30)
	private String email;
	@Column(length = 30)
	private Long ssn;
	@Column(length = 30)
	private String gender;
	@Column(length = 30)
	private Long phoneNo;
	@Column(length = 30)
	private String stateName;
	private LocalDate dob;
	private String createdBy;
	private String updatedBy;
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdOn;
	@Column(insertable = false)
	@UpdateTimestamp
	private LocalDateTime updatedOn;

}
