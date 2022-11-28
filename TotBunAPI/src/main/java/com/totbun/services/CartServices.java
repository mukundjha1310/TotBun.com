package com.totbun.services;

import java.util.List;
import com.totbun.exceptions.CartException;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.PaymentException;
import com.totbun.modules.Cart;
import com.totbun.modules.Order;

public interface CartServices {
	
	public Cart seeCartDetails(Integer userId) throws LogException, CartException;
	
	public String placeOrder(Integer userId, String paymentMethod) throws LogException, PaymentException;
	
	public String trackOrder(Integer userId) throws LogException;
	
	public List<Order> seePreviousOrders(Integer userId) throws LogException, OrderException;
}
