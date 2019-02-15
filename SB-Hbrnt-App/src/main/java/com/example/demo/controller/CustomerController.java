package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String pingStatus() {

		return "Application is Running Fine !!";
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);

		return ResponseEntity.ok(" New Customer Added");
	}
}
