package com.totbun.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartAllDetailsDTO {
	
	private int totalProducts;
	private int totalCartPrice;
	private List<ProductDTO> productsInCart;
	
}
