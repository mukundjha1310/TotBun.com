package com.totbun.services;

import java.util.List;

import com.totbun.exceptions.LogException;
import com.totbun.exceptions.OrderException;
import com.totbun.modules.Order;

public interface OrderServices {
	
	public List<Order> viewAllOrders(Integer adminId) throws LogException, OrderException;
	
	public Order updateOrderStatus(Integer adminId, Integer orderId, String status) throws LogException, OrderException;
	
	public List<Order> searchOrdersByUserEmail(Integer adminId, String email) throws LogException, OrderException;
	
	public Order searchOrderByOrderId(Integer adminId, Integer orderId) throws LogException, OrderException;
	
	public List<Order> seeCancelledOrders(Integer adminId) throws LogException, OrderException;
	
	
}
