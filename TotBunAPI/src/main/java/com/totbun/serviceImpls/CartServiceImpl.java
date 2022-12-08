package com.totbun.serviceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totbun.DTOs.CartAllDetailsDTO;
import com.totbun.DTOs.ProductDTO;
import com.totbun.exceptions.CartException;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.ProductException;
import com.totbun.modules.Cart;
import com.totbun.modules.CurrentUserSession;
import com.totbun.modules.Product;
import com.totbun.repositories.CartRepo;
import com.totbun.repositories.ProductRepo;
import com.totbun.repositories.UserLogRepo;
import com.totbun.repositories.UserRepo;
import com.totbun.services.CartServices;

@Service
public class CartServiceImpl implements CartServices{
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private ProductRepo pRepo;
	
	@Autowired
	private CartRepo cRepo;
	
	@Autowired
	private UserLogRepo ulRepo;

	@SuppressWarnings("deprecation")
	@Override
	public String addProductToCart(Integer userId, Integer productId, Integer quantity) throws LogException, ProductException {
		
		Optional<CurrentUserSession> cuser = ulRepo.findById(userId);
		
		if(cuser.isPresent())
		{
			Optional<Product> product = pRepo.findById(productId);
			if(product.isPresent())
			{
				Product product1 = product.get();
				Cart cart = new Cart();
				cart.setUser(uRepo.getById(userId));
				cart.setProduct(product1);
				cart.setQuantity(quantity);
				cart.setTotalPrice(quantity*product.get().getPrice());
				
				cRepo.save(cart);
				
				return "Product Added to cart...";
				
			}
			else throw new ProductException("Product does not exist with product Id "+productId);
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
	}
	
	@Override
	public CartAllDetailsDTO seeCartDetails(Integer userId) throws LogException, CartException {
		
		Optional<CurrentUserSession> cuser = ulRepo.findById(userId);
		
		if(cuser.isPresent())
		{
			List<Cart> cartDetails = cRepo.findByUser(userId);
			
			CartAllDetailsDTO result = new CartAllDetailsDTO();
			
			List<ProductDTO> productsInCart = new ArrayList<>();
			
			int totalProducts = 0;
			int totalCartPrice = 0;
			
			if(cartDetails.size() == 0)
				throw new CartException("Cart is empty...");
			else
			{
				for(Cart c:cartDetails)
				{
					Product prod = c.getProduct();
					
					ProductDTO cartProduct = new ProductDTO(prod.getProductId(), 
							prod.getProductTitle(), prod.getImage(), 
							c.getTotalPrice(), c.getQuantity(), prod.getProductRating(), 
							prod.getProductCategory(), prod.getBrand());
					
					productsInCart.add(cartProduct);
					totalProducts += c.getQuantity();
					totalCartPrice += c.getTotalPrice();
				}
			}
			
			result.setTotalProducts(totalProducts);
			result.setTotalCartPrice(totalCartPrice);
			result.setProductsInCart(productsInCart);
			
			return result;
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
	}
	
	@Override
	public String deleteProductFromCart(Integer userId, Integer productId)
			throws LogException, CartException {
		
		Optional<CurrentUserSession> cuser = ulRepo.findById(userId);
		
		if(cuser.isPresent())
		{
			Cart cart = cRepo.findCartByProductId(userId, productId);
			
			if(cart != null)
			{
				cRepo.delete(cart);
				return "Product deleted from cart...";	
			}
			else throw new CartException("Product does not exist in cart with product Id "+productId);
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
		
	}

}
