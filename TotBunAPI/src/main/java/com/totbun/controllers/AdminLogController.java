package com.totbun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.totbun.DTOs.AdminLoginDTO;
import com.totbun.exceptions.LogException;
import com.totbun.services.AdminLogService;

@RestController
@RequestMapping("/totbun")
public class AdminLogController {
	
	@Autowired
	private AdminLogService aLogServ;
	
	@PostMapping("/adminlogin")
	public ResponseEntity<String> adminLogIn(@RequestBody AdminLoginDTO dto) throws LogException{
		
		String result = aLogServ.adminLogIn(dto);
		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/adminlogout/{adminId}")
	public ResponseEntity<String> adminLogOut(@PathVariable("adminId") Integer adminId) throws LogException {
		
		String result = aLogServ.adminLogOut(adminId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
