package com.totbun.services;

import java.util.List;
import com.totbun.exceptions.ProductException;
import com.totbun.DTOs.AdminRegisterDTO;
import com.totbun.DTOs.CustomerDTO;
import com.totbun.exceptions.AdminException;
import com.totbun.exceptions.CustomerException;
import com.totbun.modules.Product;
import com.totbun.modules.Customer;

public interface AdminServices {
	
	public Product addNewProduct(Product product) throws ProductException;
	
	public Product seeIndividualProductDetails(Integer productId) throws ProductException;
	
	public List<Product> seeAllProductsDetails() throws ProductException;
	
	public Product updateProductQuantity(Integer productId, Integer productQty) throws ProductException;
	
	public List<CustomerDTO> viewAllCustomers() throws CustomerException;
	
	public Customer viewCustomerById(Integer customerId) throws CustomerException;

	public String registerAdmin(AdminRegisterDTO adminDetails) throws AdminException;
}
