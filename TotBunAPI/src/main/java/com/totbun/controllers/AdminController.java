package com.totbun.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.ProductException;
import com.totbun.exceptions.UserException;
import com.totbun.modules.Product;
import com.totbun.modules.User;
import com.totbun.services.AdminServices;

@RestController
@RequestMapping("/totbun")
public class AdminController {
	
	@Autowired
	private AdminServices aService;
	
	@PostMapping("/admin/{adminId}")
	public ResponseEntity<Product> AddNewProduct(@PathVariable("adminId") Integer adminId, @RequestBody Product product) throws LogException, ProductException
	{
		Product p1 = aService.addNewProduct(adminId, product);
		
		return new ResponseEntity<Product>(p1, HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/{adminId}/{productId}/{newProductQty}")
	public ResponseEntity<Product> updateProductQuantity(@PathVariable("adminId") Integer adminId, @PathVariable("productId") Integer productId, @PathVariable("newProductQty") Integer newProductQty) throws LogException, ProductException
	{
		Product p1 = aService.updateProductQuantity(adminId, productId, newProductQty);
		
		return new ResponseEntity<Product>(p1, HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/{adminId}")
	public ResponseEntity<List<User>> viewAllUsers(@PathVariable("adminId") Integer adminId) throws UserException, LogException
	{
		List<User> users = aService.viewAllUsers(adminId);
		
		return new ResponseEntity<List<User>>(users, HttpStatus.CREATED);
	}

}