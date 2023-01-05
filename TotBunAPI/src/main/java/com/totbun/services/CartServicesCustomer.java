package com.totbun.services;

import com.totbun.DTOs.CartAllDetailsDTO;
import com.totbun.exceptions.CartException;
import com.totbun.exceptions.ProductException;

public interface CartServicesCustomer {
	
	public String addProductToCart(Integer productId, Integer quantity) throws ProductException;
	
	public CartAllDetailsDTO seeCartDetails() throws CartException;
	
	public String deleteProductFromCart(Integer productId) throws CartException;
	
}
