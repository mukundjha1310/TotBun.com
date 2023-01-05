package com.totbun.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private Integer productId;
	private String productTitle;
	private String image;
	private Integer price;
	private Integer quantity;
	private Integer productRating;
	private String productCategory;
	private String brand;
	
}
