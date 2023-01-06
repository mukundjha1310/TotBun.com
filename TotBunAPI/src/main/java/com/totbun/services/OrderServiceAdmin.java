package com.totbun.services;

import java.util.List;
import com.totbun.DTOs.OrderDTO;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.CustomerException;

public interface OrderServiceAdmin {
	
	public List<OrderDTO> seeAllOrdersDetails() throws OrderException;
	
	public OrderDTO updateOrderStatus(Integer orderId, String newOrderStatus) throws OrderException;
	
	public List<OrderDTO> searchOrdersByCustomerId(Integer customerId) throws OrderException, CustomerException;

}
