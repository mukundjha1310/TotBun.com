package com.totbun.services;

import com.totbun.exceptions.CartException;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.PaymentException;
import com.totbun.exceptions.ProductException;
import com.totbun.modules.Cart;

public interface CartServices {
	
	public Cart seeCartDetails(Integer userId) throws LogException, CartException;
	
	
	
	public String trackOrder(Integer userId) throws LogException;
	
	
}
