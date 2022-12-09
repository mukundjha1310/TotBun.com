package com.totbun.services;

import com.totbun.DTOs.CartAllDetailsDTO;
import com.totbun.exceptions.CartException;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.ProductException;

public interface CartServicesUser {
	
	public String addProductToCart(Integer userId, Integer productId, Integer quantity) throws LogException, ProductException;
	
	public CartAllDetailsDTO seeCartDetails(Integer userId) throws LogException, CartException;
	
	public String deleteProductFromCart(Integer userId, Integer productId) throws LogException, CartException;
	
}
