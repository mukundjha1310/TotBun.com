package com.totbun.services;

import java.util.List;
import com.totbun.exceptions.ProductException;
import com.totbun.exceptions.CustomerException;
import com.totbun.modules.Product;
import com.totbun.modules.Customer;

public interface AdminServices {
	
	public Product addNewProduct(Product product) throws ProductException;
	
	public Product seeIndividualProductDetails(Integer productId) throws ProductException;
	
	public List<Product> seeAllProductsDetails() throws ProductException;
	
	public Product updateProductQuantity(Integer productId, Integer productQty) throws ProductException;
	
	public List<Customer> viewAllCustomers() throws CustomerException;
	
	public Customer viewCustomerById(Integer customerId) throws CustomerException;
}
