package com.nt.service;

import com.nt.bindings.CitizenAppRegistrationInputs;
import com.nt.exceptions.InvalidSSNException;

public interface ICitizenAppRegistrationService {

	public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs) throws InvalidSSNException;

}
