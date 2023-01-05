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
public class Sales {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer salesId;
	
	@ManyToOne
	@JoinColumn(name = "user_Id")
	private Customer user;
	
	@ManyToOne
	@JoinColumn(name = "product_Id")
	private Product product;
	
	private Integer quantity;
	
	private Integer totalPrice;
	
	private LocalDate deliveryDate;
	
	private String paymentType;
	
	private String OrderStatus;
}
