package com.nt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.nt.bindings.CitizenAppRegistrationInputs;
import com.nt.entity.CitizenAppRegistrationEntity;
import com.nt.exceptions.InvalidSSNException;
import com.nt.repository.ICitizenAppRegistrationRepository;

import reactor.core.publisher.Mono;

@Service
public class CitizenAppRegistrationServiceImpl implements ICitizenAppRegistrationService {
	@Autowired
	private ICitizenAppRegistrationRepository citizenRepo;
	@Autowired
	private RestTemplate template;
	@Autowired
	private WebClient client;
	@Value("${ar.stateName}")
	private String targetState;
	@Value("${ar.endPointUrl}")
	private String ssaWebUrl;

	@Override
	public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs) throws InvalidSSNException {

		// provide externalssa-web url
		// String ssaWebUrl="http://localhost:9090/ssa-web-api/getssn/{ssn}";
		/*
		 * HttpHeaders headers=new HttpHeaders();
		 * headers.set("accept","applicatio/json"); HttpEntity entity=new
		 * HttpEntity(headers);
		 */
		// ResponseEntity<String> response = template.exchange(ssaWebUrl,
		// HttpMethod.GET, null, String.class,inputs.getSsn());
		String stateName = client.get().uri(ssaWebUrl, inputs.getSsn()).retrieve().bodyToMono(String.class).block();
		/*Mono<String> response = client.get().uri(ssaWebUrl, inputs.getSsn()).retrieve().onStatus(HttpStatus.BAD_REQUEST::equals,res->res.bodyToMono(String.class)
				.map(ex->new InvalidSSNException("invalid ssn"))).bodyToMono(String.class);
		String stateName = response.block();*/
		// get state name
		// String stateName = response.getBody();
		System.out.println(stateName);
		System.out.println(targetState);
		if (stateName.equalsIgnoreCase(targetState)) {
			CitizenAppRegistrationEntity entity = new CitizenAppRegistrationEntity();

			BeanUtils.copyProperties(inputs, entity);
			entity.setStateName(stateName);

			// save object using repo
			int id = citizenRepo.save(entity).getAppId();
			return id;
		}
		throw new InvalidSSNException("invalid ssn exception");

	}

}
