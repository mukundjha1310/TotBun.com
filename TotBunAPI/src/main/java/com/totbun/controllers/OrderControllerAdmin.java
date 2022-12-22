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
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.UserException;
import com.totbun.modules.Orders;
import com.totbun.services.OrderServiceAdmin;

@RestController
@RequestMapping("/totbun")
public class OrderControllerAdmin {
	
	@Autowired
	private OrderServiceAdmin oService;
	
	@GetMapping("/order/{adminId}")
	public ResponseEntity<List<Orders>> seeAllOrdersDetails(@PathVariable("adminId") Integer adminId) throws LogException, OrderException
	{
		List<Orders> orders  = oService.seeAllOrdersDetails(adminId);
		
		return new ResponseEntity<List<Orders>>(orders, HttpStatus.FOUND);
	}
	
	@PatchMapping("/order/{adminId}/{orderId}/{newOrderStatus}")
	public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable("adminId") Integer adminId, 
			@PathVariable("orderId") Integer orderId, @PathVariable("newOrderStatus") String newOrderStatus) throws LogException, OrderException
	{
		OrderDTO order  = oService.updateOrderStatus(adminId, orderId, newOrderStatus);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.FOUND);
	}
	
	@GetMapping("/order/{adminId}/{userId}")
	public ResponseEntity<List<Orders>> searchOrdersByUserId(@PathVariable("adminId") Integer adminId, @PathVariable("userId") Integer userId) throws LogException, OrderException, UserException
	{
		List<Orders> orders  = oService.searchOrdersByUserId(adminId, userId);
		
		return new ResponseEntity<List<Orders>>(orders, HttpStatus.FOUND);
	}

}
