package com.totbun.services;

import java.util.List;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.UserException;
import com.totbun.modules.Orders;

public interface OrderServiceAdmin {
	
	public List<Orders> seeAllOrdersDetails(Integer adminId) throws LogException, OrderException;
	
	public Orders updateOrderStatus(Integer adminId, Integer orderId, String newOrderStatus) throws LogException, OrderException;
	
	public List<Orders> searchOrdersByUserId(Integer adminId, Integer userId) throws LogException, OrderException, UserException;

}
