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
import com.totbun.exceptions.ProductException;
import com.totbun.services.CartServicesCustomer;

@RestController
@RequestMapping("/totbun")
public class CartController {
	
	@Autowired
	private CartServicesCustomer cService;
	
	@PostMapping("/cart/add/{productId}/{quantity}")
	public ResponseEntity<String> addProductToCart(@PathVariable("productId") Integer productId, @PathVariable("quantity") Integer quantity) throws ProductException
	{
		String message = cService.addProductToCart(productId, quantity);
		
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@GetMapping("/cart/see-cart-details")
	public ResponseEntity<CartAllDetailsDTO> seeCartDetails() throws CartException, ProductException
	{
		CartAllDetailsDTO message = cService.seeCartDetails();
		
		return new ResponseEntity<CartAllDetailsDTO>(message, HttpStatus.FOUND);
	}
	
	@DeleteMapping("/cart/delete/{productId}")
	public ResponseEntity<String> deleteProductFromCart(@PathVariable("productId") Integer productId) throws CartException
	{
		String message = cService.deleteProductFromCart(productId);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
