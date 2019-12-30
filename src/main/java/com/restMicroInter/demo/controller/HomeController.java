package com.restMicroInter.demo.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.restMicroInter.demo.model.User;
import com.restMicroInter.demo.service.UserService;

/**
 * @author sneha.paliwal
 *
 */
@RestController
public class HomeController {
	String baseUrl="http://localhost:8080/user/all";
	RestTemplate restTemplate=new RestTemplate();
	
	@Autowired
	UserService userService;
	
	@GetMapping(path = "/inter", produces = "application/json", consumes = "application/json")
	public HashMap<Integer,Integer> getTotalBalance() {
		
		ParameterizedTypeReference<List<User>> typeRef=new ParameterizedTypeReference<List<User>>() {
		};
		ResponseEntity<List<User>> response=restTemplate.exchange(baseUrl, 
				HttpMethod.GET, getHeaders(), typeRef);
		
		List<User> userBody=(List<User>) response.getBody();
		HashMap<Integer,Integer> resultMap=userService.getTotalBalanceForUser(userBody);
		return resultMap;
	}

	private HttpEntity<Object> getHeaders() {
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<>(headers);
	}

}
