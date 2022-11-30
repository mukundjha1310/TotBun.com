package com.totbun.modules;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private Integer quantity;
	
	private LocalDateTime dateTime;
	private String paymentMethod;
	private String orderStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<Product> products = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Sales sales;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private OrderHistory orderHistory;
	
}
