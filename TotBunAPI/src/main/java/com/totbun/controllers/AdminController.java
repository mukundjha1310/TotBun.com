package com.totbun.controllers;

import java.util.List;
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
import com.totbun.exceptions.ProductException;
import com.totbun.exceptions.CustomerException;
import com.totbun.modules.Product;
import com.totbun.modules.Customer;
import com.totbun.services.AdminServices;
import com.totbun.services.CustomerServices;

@RestController
@RequestMapping("/totbun")
public class AdminController {
	
	@Autowired
	private AdminServices aService;
	
	@Autowired
	private CustomerServices cService;
	
	@PostMapping("/admin/add-new-product")
	public ResponseEntity<Product> AddNewProduct(@RequestBody Product product) throws ProductException
	{
		Product p1 = aService.addNewProduct(product);
		
		return new ResponseEntity<Product>(p1, HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/see-individual-product-details/{productId}")
	public ResponseEntity<Product> seeIndividualProductDetails(@PathVariable("productId") Integer productId) throws ProductException
	{
		Product p1 = aService.seeIndividualProductDetails(productId);
		
		return new ResponseEntity<Product>(p1, HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/see-all-products-details")
	public ResponseEntity<List<Product>> seeAllProductsDetails() throws ProductException
	{
		List<Product> products = aService.seeAllProductsDetails();
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/update-product-quantity/{productId}/{newProductQty}")
	public ResponseEntity<Product> updateProductQuantity(@PathVariable("productId") Integer productId, @PathVariable("newProductQty") Integer newProductQty) throws ProductException
	{
		Product p1 = aService.updateProductQuantity(productId, newProductQty);
		
		return new ResponseEntity<Product>(p1, HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/view-all-customers")
	public ResponseEntity<List<Customer>> viewAllCustomers() throws CustomerException
	{
		List<Customer> users = aService.viewAllCustomers();
		
		return new ResponseEntity<List<Customer>>(users, HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/view-customer-by-id/{customerId}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable("customerId") Integer customerId) throws CustomerException 
	{
		Customer user1 = aService.viewCustomerById(customerId);
		
		return new ResponseEntity<Customer>(user1, HttpStatus.FOUND);
	}
	

	@DeleteMapping("/admin/delete-customer/{customerId}")
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable("customerId") Integer customerId) throws CustomerException
	{
		Customer user1 = cService.deleteCustomer(customerId);
		
		return new ResponseEntity<Customer>(user1, HttpStatus.OK);
	}
	

}
