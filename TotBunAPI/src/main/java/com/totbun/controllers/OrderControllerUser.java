package com.totbun.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.totbun.DTOs.OrderHistoryDTO;
import com.totbun.exceptions.CartException;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.PaymentException;
import com.totbun.services.OrderServiceUser;

@RestController
@RequestMapping("/totbun")
public class OrderControllerUser {
	
	@Autowired
	private OrderServiceUser oService;
	
	@PostMapping("/order/{userId}/{paymentType}")
	public ResponseEntity<String> placeOrder(@PathVariable("userId") Integer userId, @PathVariable("paymentType") String paymentType) throws LogException, CartException, PaymentException
	{
		String message  = oService.placeOrder(userId, paymentType);
		
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/order/{userId}")
	public ResponseEntity<List<OrderHistoryDTO>> seeOrderHistory(@PathVariable("userId") Integer userId) throws LogException, OrderException
	{
		List<OrderHistoryDTO> orderHistories  = oService.seeOrderHistory(userId);
		
		return new ResponseEntity<List<OrderHistoryDTO>>(orderHistories, HttpStatus.FOUND);
	}

}
