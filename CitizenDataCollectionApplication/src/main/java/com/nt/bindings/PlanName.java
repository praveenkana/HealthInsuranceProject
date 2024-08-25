package com.nt.bindings;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PlanName {
	private Integer planId;
	private Integer caseNo;
	private String planName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String activeSw;

}
