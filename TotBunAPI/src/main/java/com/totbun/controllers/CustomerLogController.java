package com.totbun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.totbun.DTOs.CustomerLoginDTO;
import com.totbun.payload.response.JwtResponse;
import com.totbun.security.jwt.JWTUtils;
import com.totbun.security.services.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/totbun")
public class CustomerLogController {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@PostMapping("/customers/customer-login")
	public ResponseEntity<?> customerLogIn(@RequestBody CustomerLoginDTO loginDetails) 
	{
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDetails.getEmailId(), loginDetails.getPassword()));
		
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginDetails.getEmailId());
		
		if(userDetails != null)
			return ResponseEntity.ok(new JwtResponse(jwtUtils.generateToken(userDetails)));
		
		return new ResponseEntity<>("Invalid admin log in details..!", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/customers/customer-logout")
	public ResponseEntity<String> customerLogOut()
	{
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
}
