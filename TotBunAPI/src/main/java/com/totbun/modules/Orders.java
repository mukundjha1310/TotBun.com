package com.totbun.modules;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	@ManyToOne
	@JoinColumn(name = "customer_Id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "product_Id")
	private Product product;
	
	private Integer quantity;
	
	private Integer totalPrice;
	
	private LocalDate orderDate;
	
	private String paymentType;
	
	private String OrderStatus;
}
