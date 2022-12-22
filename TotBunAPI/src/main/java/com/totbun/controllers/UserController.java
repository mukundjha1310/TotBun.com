package com.totbun.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.UserException;
import com.totbun.modules.User;
import com.totbun.services.UserServices;

@RestController
@RequestMapping("/totbun")
public class UserController {
	
	@Autowired
	private UserServices uService;
	
	@PostMapping("/users/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws UserException
	{
		User user1 = uService.registerUser(user);
		
		return new ResponseEntity<User>(user1, HttpStatus.CREATED);
	}
	
	@PutMapping("/users/update")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user) throws UserException, LogException
	{
		User user1 = uService.updateUser(user);
		
		return new ResponseEntity<User>(user1, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/delete/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable("userId") Integer userId) throws UserException, LogException
	{
		User user1 = uService.deleteUser(userId);
		
		return new ResponseEntity<User>(user1, HttpStatus.OK);
	}
	
	@GetMapping("/users/pesonal-details")
	public ResponseEntity<User> viewPersonalDetails() throws LogException
	{
		User user1 = uService.viewPersonalDetails();
		
		return new ResponseEntity<User>(user1, HttpStatus.FOUND);
	}
	
	
	
}
