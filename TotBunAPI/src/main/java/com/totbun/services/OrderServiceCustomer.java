package com.totbun.services;

import java.util.List;
import com.totbun.DTOs.OrderHistoryDTO;
import com.totbun.exceptions.CartException;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.PaymentException;

public interface OrderServiceCustomer {
	
	public String placeOrder(String paymentType) throws CartException, PaymentException;
	
	public List<OrderHistoryDTO> seeOrderHistory() throws OrderException;


}
