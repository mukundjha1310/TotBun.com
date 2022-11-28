package com.totbun.services;

import com.totbun.exceptions.LogException;
import com.totbun.exceptions.ProductException;
import com.totbun.modules.Product;

public interface AdminServices {
	
	public Product addNewProduct(Integer AdminId, Product product) throws LogException, ProductException;
	public Product updateProductQuantity(Integer AdminId, Integer productQty) throws LogException, ProductException;
}
