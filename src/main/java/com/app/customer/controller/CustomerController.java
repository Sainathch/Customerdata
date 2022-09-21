package com.app.customer.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.customer.service.CustomerService;



@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	

	@PostMapping(path = "/", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, HashMap<String, String>> addUser(@RequestBody ArrayList<HashMap<String, String>> ar){
		HashMap<String, String> hm = new HashMap<>();
		ArrayList<HashMap<String, String>> out = new ArrayList<>();
		out.add(hm);
		return customerService.calcForCustomers(ar);
	}


}
