package com.totbun.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.totbun.DTOs.OrderDTO;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.CustomerException;
import com.totbun.modules.Orders;
import com.totbun.services.OrderServiceAdmin;

@RestController
@RequestMapping("/totbun")
public class OrderControllerAdmin {
	
	@Autowired
	private OrderServiceAdmin oService;
	
	@GetMapping("/order/see-all-orders-details")
	public ResponseEntity<List<Orders>> seeAllOrdersDetails() throws OrderException
	{
		List<Orders> orders  = oService.seeAllOrdersDetails();
		
		return new ResponseEntity<List<Orders>>(orders, HttpStatus.FOUND);
	}
	
	@PatchMapping("/order/{orderId}/{newOrderStatus}")
	public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable("orderId") Integer orderId, @PathVariable("newOrderStatus") String newOrderStatus) throws OrderException
	{
		OrderDTO order  = oService.updateOrderStatus(orderId, newOrderStatus);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.FOUND);
	}
	
	@GetMapping("/order/search-order{customerId}")
	public ResponseEntity<List<Orders>> searchOrdersByCustomerId(@PathVariable("customerId") Integer customerId) throws OrderException, CustomerException
	{
		List<Orders> orders  = oService.searchOrdersByCustomerId(customerId);
		
		return new ResponseEntity<List<Orders>>(orders, HttpStatus.FOUND);
	}

}
