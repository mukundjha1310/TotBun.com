package com.totbun.services;

import java.util.List;

import com.totbun.DTOs.OrderHistoryDTO;
import com.totbun.exceptions.CartException;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.PaymentException;

public interface OrderServiceUser {
	
	public String placeOrder(Integer userId, String paymentType) throws LogException, CartException, PaymentException;
	
	public List<OrderHistoryDTO> seeOrderHistory(Integer userId) throws LogException, OrderException;


}
