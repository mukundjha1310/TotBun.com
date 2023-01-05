package com.totbun.DTOs;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderHistoryDTO {
	
	private ProductDTO product;
	
	private LocalDate orderDate;
	
	private String paymentType;
	
	private String OrderStatus;
}
