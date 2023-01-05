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
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.PaymentException;
import com.totbun.services.OrderServiceCustomer;

@RestController
@RequestMapping("/totbun")
public class OrderControllerCustomer {
	
	@Autowired
	private OrderServiceCustomer oService;
	
	@PostMapping("/order/place-order/{paymentType}")
	public ResponseEntity<String> placeOrder(@PathVariable("paymentType") String paymentType) throws CartException, PaymentException
	{
		String message  = oService.placeOrder(paymentType);
		
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/order/history/order-history")
	public ResponseEntity<List<OrderHistoryDTO>> seeOrderHistory() throws OrderException
	{
		List<OrderHistoryDTO> orderHistories  = oService.seeOrderHistory();
		
		return new ResponseEntity<List<OrderHistoryDTO>>(orderHistories, HttpStatus.FOUND);
	}

}
