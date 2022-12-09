package com.totbun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.totbun.DTOs.CartAllDetailsDTO;
import com.totbun.exceptions.CartException;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.ProductException;
import com.totbun.services.CartServicesUser;

@RestController
@RequestMapping("/totbun")
public class CartControllerUser {
	
	@Autowired
	private CartServicesUser cService;
	
	@PostMapping("/cart/{userId}/{productId}/{quantity}")
	public ResponseEntity<String> addProductToCart(@PathVariable("userId") Integer userId, @PathVariable("productId") Integer productId, @PathVariable("quantity") Integer quantity) throws LogException, ProductException
	{
		String message = cService.addProductToCart(userId, productId, quantity);
		
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@GetMapping("/cart/see-cart-details/{userId}")
	public ResponseEntity<CartAllDetailsDTO> seeCartDetails(@PathVariable("userId") Integer userId) throws LogException, CartException, ProductException
	{
		CartAllDetailsDTO message = cService.seeCartDetails(userId);
		
		return new ResponseEntity<CartAllDetailsDTO>(message, HttpStatus.FOUND);
	}
	
	@DeleteMapping("/cart/{userId}/{productId}")
	public ResponseEntity<String> deleteProductFromCart(@PathVariable("userId") Integer userId, @PathVariable("productId") Integer productId) throws LogException, CartException
	{
		String message = cService.deleteProductFromCart(userId, productId);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
