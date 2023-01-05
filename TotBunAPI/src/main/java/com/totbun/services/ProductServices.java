package com.totbun.services;

import java.util.List;
import com.totbun.DTOs.ShowProductDTO;
import com.totbun.exceptions.ProductException;

public interface ProductServices {
	
	public List<ShowProductDTO> searchProductsByName(String productName) throws ProductException;
	
	public List<ShowProductDTO> searchProductsByCategory(String productCategory) throws ProductException;
	
	public List<ShowProductDTO> sortProductsByPriceLowToHigh(String productCategory) throws ProductException;
	
	public List<ShowProductDTO> sortProductsByPriceHighToLow(String productCategory) throws ProductException;
	
	public List<ShowProductDTO> sortProductsByRatingLowToHigh(String productCategory) throws ProductException;
	
	public List<ShowProductDTO> sortProductsByRatingHighToLow(String productCategory) throws ProductException;
	
	public ShowProductDTO seeIndividualProductsDetails(Integer productId) throws ProductException;
	
	
	
}
