package com.totbun.modules;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	private String productTitle;
	private String image;
	private Integer price;
	private Integer quantity;
	private String productRating;
	private String productCategory;
	private String brand;
	
	
	
	
}
