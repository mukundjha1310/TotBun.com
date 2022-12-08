package com.totbun.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowProductDTO {
	
	private Integer productId;
	private String productTitle;
	private String image;
	private Integer price;
	private Integer productRating;
	private String productCategory;
	private String brand;
	
}
