package com.totbun.serviceImpls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.totbun.DTOs.ShowProductDTO;
import com.totbun.exceptions.ProductException;
import com.totbun.modules.Product;
import com.totbun.repositories.ProductRepo;
import com.totbun.services.ProductServices;

@Service
public class ProductServiceImpl implements ProductServices{
	
	@Autowired
	private ProductRepo pRepo;
	
	@Override
	public List<ShowProductDTO> searchProductsByName(String productName) throws ProductException {
		
		List<Product> products = pRepo.findByProductTitleContainingIgnoreCase(productName);
		
		if(products.size() > 0)
		{
			List<ShowProductDTO> result = new ArrayList<>();
			
			for(Product p:products)
			{
				ShowProductDTO showProduct = new ShowProductDTO(p.getProductId(), 
						p.getProductTitle(), p.getImage(), 
						p.getPrice(), p.getProductRating(), 
						p.getProductCategory(), p.getBrand());
				result.add(showProduct);
			}
			
			return result;
		}
		else throw new ProductException("No product found with the name "+productName);
	}

	@Override
	public List<ShowProductDTO> searchProductsByCategory(String productCategory) throws ProductException {
		
		List<Product> products = pRepo.findByProductCategoryContainingIgnoreCase(productCategory);
		
		if(products.size() > 0)
		{
			List<ShowProductDTO> result = new ArrayList<>();
			
			for(Product p:products)
			{
				ShowProductDTO showProduct = new ShowProductDTO(p.getProductId(), 
						p.getProductTitle(), p.getImage(), 
						p.getPrice(), p.getProductRating(), 
						p.getProductCategory(), p.getBrand());
				result.add(showProduct);
			}
			
			return result;
		}
		else throw new ProductException("No product found in this category "+productCategory);
	}

	@Override
	public List<ShowProductDTO> sortProductsByPriceLowToHigh(String productCategory) throws ProductException {
		
		List<Product> products = pRepo.findByProductCategoryContainingIgnoreCase(productCategory);
		
		if(products.size() > 0)
		{
			Collections.sort(products, new Comparator<Product>() {
				public int compare(Product p1, Product p2)
				{
					return p1.getPrice() - p2.getPrice();
				}
			});
			
			List<ShowProductDTO> result = new ArrayList<>();
			
			for(Product p:products)
			{
				ShowProductDTO showProduct = new ShowProductDTO(p.getProductId(), 
						p.getProductTitle(), p.getImage(), 
						p.getPrice(), p.getProductRating(), 
						p.getProductCategory(), p.getBrand());
				result.add(showProduct);
			}
			
			return result;
		}
		else throw new ProductException("Product not found in this category "+productCategory);
		
	}

	@Override
	public List<ShowProductDTO> sortProductsByPriceHighToLow(String productCategory) throws ProductException {
		
		List<Product> products = pRepo.findByProductCategoryContainingIgnoreCase(productCategory);
		
		if(products.size() > 0)
		{
			Collections.sort(products, new Comparator<Product>() {
				public int compare(Product p1, Product p2)
				{
					return p2.getPrice() - p1.getPrice();
				}
			});
			
			List<ShowProductDTO> result = new ArrayList<>();
			
			for(Product p:products)
			{
				ShowProductDTO showProduct = new ShowProductDTO(p.getProductId(), 
						p.getProductTitle(), p.getImage(), 
						p.getPrice(), p.getProductRating(), 
						p.getProductCategory(), p.getBrand());
				result.add(showProduct);
			}
			
			return result;
		}
		else throw new ProductException("Products not found in this category "+productCategory);
	}

	@Override
	public List<ShowProductDTO> sortProductsByRatingLowToHigh(String productCategory) throws ProductException {
		
		List<Product> products = pRepo.findByProductCategoryContainingIgnoreCase(productCategory);
		
		if(products.size() > 0)
		{
			Collections.sort(products, new Comparator<Product>() {
				public int compare(Product p1, Product p2)
				{
					return p1.getProductRating() - p2.getProductRating();
				}
			});
			
			List<ShowProductDTO> result = new ArrayList<>();
			
			for(Product p:products)
			{
				ShowProductDTO showProduct = new ShowProductDTO(p.getProductId(), 
						p.getProductTitle(), p.getImage(), 
						p.getPrice(), p.getProductRating(), 
						p.getProductCategory(), p.getBrand());
				result.add(showProduct);
			}
			
			return result;
		}
		else throw new ProductException("Products not found in this category "+productCategory);
	}

	@Override
	public List<ShowProductDTO> sortProductsByRatingHighToLow(String productCategory) throws ProductException {

		List<Product> products = pRepo.findByProductCategoryContainingIgnoreCase(productCategory);
		
		if(products.size() > 0)
		{
			Collections.sort(products, new Comparator<Product>() {
				public int compare(Product p1, Product p2)
				{
					return p2.getProductRating() - p1.getProductRating();
				}
			});
			
			List<ShowProductDTO> result = new ArrayList<>();
			
			for(Product p:products)
			{
				ShowProductDTO showProduct = new ShowProductDTO(p.getProductId(), 
						p.getProductTitle(), p.getImage(), 
						p.getPrice(), p.getProductRating(), 
						p.getProductCategory(), p.getBrand());
				result.add(showProduct);
			}
			
			return result;
		}
		else throw new ProductException("Products not found in this category "+productCategory);
	}

	@Override
	public ShowProductDTO seeIndividualProductsDetails(Integer productId) throws ProductException {
		
		Optional<Product> product = pRepo.findById(productId);
		
		if(product != null)
		{
			Product p = product.get();
			ShowProductDTO showProduct = new ShowProductDTO(p.getProductId(), 
					p.getProductTitle(), p.getImage(), 
					p.getPrice(), p.getProductRating(), 
					p.getProductCategory(), p.getBrand());
			return showProduct;
		}
		else throw new ProductException("Product not found with id: "+productId);
		
	}

}
