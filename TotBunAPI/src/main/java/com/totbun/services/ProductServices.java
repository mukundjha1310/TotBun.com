package com.totbun.services;

import java.util.List;

import com.totbun.exceptions.LogException;
import com.totbun.exceptions.ProductException;
import com.totbun.modules.Product;

public interface ProductServices {
	
	public List<Product> SearchProductByName(String productName) throws ProductException;
	
	public List<Product> SearchProductByCategory(String productCategory) throws ProductException;
	
	public List<Product> sortProductsByPriceLowToHigh() throws ProductException;
	
	public List<Product> sortProductsByPriceHighToLow() throws ProductException;
	
	public List<Product> sortProductsByRatingLowToHigh() throws ProductException;
	
	public List<Product> sortProductsByRatingHighToLow() throws ProductException;
	
	public Product seeIndividualProductDetails(Integer productId) throws ProductException;
	
	
	
}
