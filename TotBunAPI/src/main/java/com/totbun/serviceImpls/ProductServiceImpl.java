package com.totbun.serviceImpls;

import java.util.ArrayList;
import java.util.List;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShowProductDTO> sortProductsByPriceLowToHigh() throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShowProductDTO> sortProductsByPriceHighToLow() throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShowProductDTO> sortProductsByRatingLowToHigh() throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShowProductDTO> sortProductsByRatingHighToLow() throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShowProductDTO seeIndividualProductsDetails(Integer productId) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

}
