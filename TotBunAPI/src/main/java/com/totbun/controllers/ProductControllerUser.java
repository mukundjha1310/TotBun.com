package com.totbun.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.totbun.DTOs.ProductDTO;
import com.totbun.DTOs.ShowProductDTO;
import com.totbun.exceptions.ProductException;
import com.totbun.services.ProductServices;

@RestController
@RequestMapping("/totbun")
public class ProductControllerUser {
	
	@Autowired
	private ProductServices pService;
	
	@GetMapping("/products/search-by-name/{productName}")
	public ResponseEntity<List<ShowProductDTO>> searchProductsByName(@PathVariable("productName") String productName) throws ProductException
	{
		List<ShowProductDTO> products = pService.searchProductsByName(productName);
		
		return new ResponseEntity<List<ShowProductDTO>>(products, HttpStatus.FOUND);
	}
	
	@GetMapping("/products/search-by-category/{productCategory}")
	public ResponseEntity<List<ShowProductDTO>> searchProductsByCategory(@PathVariable("productCategory") String productCategory) throws ProductException
	{
		List<ShowProductDTO> products = pService.searchProductsByCategory(productCategory);
		
		return new ResponseEntity<List<ShowProductDTO>>(products, HttpStatus.FOUND);
	}
	
	@GetMapping("/products/sort-by-price-low-to-high/{productCategory}")
	public ResponseEntity<List<ShowProductDTO>> sortProductsByPriceLowToHigh(@PathVariable("productCategory") String productCategory) throws ProductException
	{
		List<ShowProductDTO> products = pService.sortProductsByPriceLowToHigh(productCategory);
		
		return new ResponseEntity<List<ShowProductDTO>>(products, HttpStatus.FOUND);
	}
	
	@GetMapping("/products/sort-by-price-high-to-low/{productCategory}")
	public ResponseEntity<List<ShowProductDTO>> sortProductsByPriceHighToLow(@PathVariable("productCategory") String productCategory) throws ProductException
	{
		List<ShowProductDTO> products = pService.sortProductsByPriceHighToLow(productCategory);
		
		return new ResponseEntity<List<ShowProductDTO>>(products, HttpStatus.FOUND);
	}
	
	@GetMapping("/products/sort-by-rating-low-to-high/{productCategory}")
	public ResponseEntity<List<ShowProductDTO>> sortProductsByRatingLowToHigh(@PathVariable("productCategory") String productCategory) throws ProductException
	{
		List<ShowProductDTO> products = pService.sortProductsByRatingLowToHigh(productCategory);
		
		return new ResponseEntity<List<ShowProductDTO>>(products, HttpStatus.FOUND);
	}
	
	@GetMapping("/products/sort-by-rating-high-to-low/{productCategory}")
	public ResponseEntity<List<ShowProductDTO>> sortProductsByRatingHighToLow(@PathVariable("productCategory") String productCategory) throws ProductException
	{
		List<ShowProductDTO> products = pService.sortProductsByRatingHighToLow(productCategory);
		
		return new ResponseEntity<List<ShowProductDTO>>(products, HttpStatus.FOUND);
	}
	
	@GetMapping("/products/see-individual-product-Details/{productId}")
	public ResponseEntity<ShowProductDTO> seeIndividualProductsDetails(@PathVariable("productId") Integer productId) throws ProductException
	{
		ShowProductDTO product = pService.seeIndividualProductsDetails(productId);
		
		return new ResponseEntity<ShowProductDTO>(product, HttpStatus.FOUND);
	}
}
