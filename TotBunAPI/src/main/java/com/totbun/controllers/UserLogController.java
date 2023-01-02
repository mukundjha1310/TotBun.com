package com.totbun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.totbun.DTOs.UserLoginDTO;
import com.totbun.payload.response.JwtResponse;
import com.totbun.security.jwt.JWTUtils;
import com.totbun.security.services.UserDetailsServiceImpl;

@RestController
@RequestMapping("/totbun")
public class UserLogController {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@PostMapping("/user/login-user")
	public ResponseEntity<?> userLogIn(@RequestBody UserLoginDTO loginDetails)
	{
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDetails.getEmailId(), loginDetails.getPassword()));
		
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginDetails.getEmailId());
		
		if(userDetails != null)
			return ResponseEntity.ok(new JwtResponse(jwtUtils.generateToken(userDetails)));
		
		return new ResponseEntity<>("Invalid admin log in details..!", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/user/logout-user")
	public ResponseEntity<String> userLogOut() {
		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
}
