package com.totbun.modules;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	
	@ManyToOne
	@JoinColumn(name = "customer_Id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "product_Id")
	private Product product;
	
	private Integer quantity;
	
	private Integer totalPrice;
	
	
	
}
