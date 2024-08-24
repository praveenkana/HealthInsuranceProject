package com.nt.bindings;

import java.util.Date;
import lombok.Data;

@Data
public class EducationInputs {
	private Integer educationId;
	private Integer caseNo;
	private String highestQualification;
	private Date passOutYear;

}
