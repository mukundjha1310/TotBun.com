package com.totbun.DTOs;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesDTO {
	
	private Integer salesId;
	
	private CustomerDTO user;
	
	private ProductDTO product;
	
	private Integer quantity;
	
	private Integer totalPrice;
	
	private LocalDate deliveryDate;
	
	private String paymentType;
	
	private String OrderStatus;
	
}
