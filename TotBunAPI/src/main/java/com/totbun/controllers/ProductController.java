package com.totbun.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.totbun.DTOs.ShowProductDTO;
import com.totbun.exceptions.ProductException;
import com.totbun.services.ProductServices;

@RestController
@RequestMapping("/totbun")
public class ProductController {
	
	@Autowired
	private ProductServices pService;
	
	@GetMapping("/products/search-by-name/{productName}")
	public ResponseEntity<List<ShowProductDTO>> searchProductsByName(@PathVariable("productName") String productName) throws ProductException
	{
		List<ShowProductDTO> products = pService.searchProductsByName(productName);
		
		return new ResponseEntity<List<ShowProductDTO>>(products, HttpStatus.FOUND);
	}
}
