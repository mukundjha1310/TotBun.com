package com.totbun.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.totbun.DTOs.CustomerRegisterDTO;
import com.totbun.exceptions.CustomerException;
import com.totbun.modules.Customer;
import com.totbun.services.CustomerServices;

@RestController
@RequestMapping("/totbun")
public class CustomerController {
	
	@Autowired
	private CustomerServices uService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/customers/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody CustomerRegisterDTO customerDetails) throws CustomerException
	{
		customerDetails.setPassword(encoder.encode(customerDetails.getPassword()));
		
		String user1 = uService.registerCustomer(customerDetails);
		
		return new ResponseEntity<String>(user1, HttpStatus.CREATED);
	}
	
	@PutMapping("/customers/update")
	public ResponseEntity<Customer> updateUser(@Valid @RequestBody Customer customer) throws CustomerException
	{
		Customer user1 = uService.updateCustomer(customer);
		
		return new ResponseEntity<Customer>(user1, HttpStatus.OK);
	}
	
	@GetMapping("/customers/view-pesonal-details")
	public ResponseEntity<Customer> viewPersonalDetails() throws CustomerException
	{
		Customer customer = uService.viewPersonalDetails();
		
		return new ResponseEntity<Customer>(customer, HttpStatus.FOUND);
	}
	
	
	
}
