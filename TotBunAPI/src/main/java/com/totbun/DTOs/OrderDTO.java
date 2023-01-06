package com.totbun.DTOs;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private Integer orderId;
	
	private Integer customerId;
	
	private Integer productId;
	
	private Integer quantity;
	
	private Integer totalPrice;
	
	private LocalDate orderDate;
	
	private String orderStatus;
	
	private String paymentType;
	
}
