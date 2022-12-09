package com.totbun.services;

import java.util.List;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.ProductException;
import com.totbun.exceptions.UserException;
import com.totbun.modules.Product;
import com.totbun.modules.User;

public interface AdminServices {
	
	
	public Product addNewProduct(Integer adminId, Product product) throws LogException, ProductException;
	
	public Product seeIndividualProductDetails(Integer adminId, Integer productId) throws LogException, ProductException;
	
	public List<Product> seeAllProductsDetails(Integer adminId) throws LogException, ProductException;
	
	public Product updateProductQuantity(Integer adminId, Integer productId, Integer productQty) throws LogException, ProductException;
	
	public List<User> viewAllUsers(Integer adminId) throws UserException, LogException;
	
	public User viewUserById(Integer adminId, Integer userId) throws UserException, LogException;
}
