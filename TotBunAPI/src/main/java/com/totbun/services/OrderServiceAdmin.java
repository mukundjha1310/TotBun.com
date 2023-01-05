package com.totbun.services;

import java.util.List;
import com.totbun.DTOs.OrderDTO;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.CustomerException;
import com.totbun.modules.Orders;

public interface OrderServiceAdmin {
	
	public List<Orders> seeAllOrdersDetails() throws OrderException;
	
	public OrderDTO updateOrderStatus(Integer orderId, String newOrderStatus) throws OrderException;
	
	public List<Orders> searchOrdersByCustomerId(Integer customerId) throws OrderException, CustomerException;

}
