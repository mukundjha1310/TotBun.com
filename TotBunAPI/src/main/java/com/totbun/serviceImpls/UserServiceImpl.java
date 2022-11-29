package com.totbun.serviceImpls;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.ProductException;
import com.totbun.exceptions.UserException;
import com.totbun.modules.Cart;
import com.totbun.modules.CurrentUserSession;
import com.totbun.modules.Product;
import com.totbun.modules.User;
import com.totbun.repositories.CartRepo;
import com.totbun.repositories.ProductRepo;
import com.totbun.repositories.UserLogRepo;
import com.totbun.repositories.UserRepo;
import com.totbun.services.UserServices;

@Service
public class UserServiceImpl implements UserServices{

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private ProductRepo pRepo;
	
	@Autowired
	private CartRepo cRepo;
	
	@Autowired
	private UserLogRepo ulRepo;
	

	@Override
	public User registerUser(User user) throws UserException, LogException {
		
		User user1 = uRepo.findByEmailId(user.getEmailId());
		
		if(user1 == null)
			return uRepo.save(user);
		else
			throw new UserException("User already exist with email Id "+user.getEmailId());
	}

	@Override
	public User updateUser(User user) throws UserException, LogException {
		
		Optional<CurrentUserSession> cusers = ulRepo.findById(user.getUserId());
		
		if(cusers.isPresent())
		{
			Optional<User> user1 = uRepo.findById(user.getUserId());
			
			if(user1.isPresent())
				return uRepo.save(user);
			else
				throw new UserException("User does not exist with user Id "+user.getUserId());
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
		
		
	}

	@Override
	public User deleteUser(Integer userId) throws UserException, LogException {
		
		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent())
		{
			Optional<User> user1 = uRepo.findById(userId);
			
			if(user1.isPresent()) 
			{
				uRepo.deleteById(userId);
				ulRepo.deleteById(userId);
				return user1.get();
			}
			else
				throw new UserException("User does not exist with user Id "+user1.get().getUserId());
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
		
		
	}

	@Override
	public User viewUser(Integer userId) throws UserException, LogException {
		
		Optional<User> user1 = uRepo.findById(userId);
		
		if(user1.isPresent()) 
			return user1.get();
		else
			throw new UserException("User does not exist with user Id "+user1.get().getUserId());
	}

	@Override
	public Cart addProductToCart(Integer userId, Integer productId) throws LogException, ProductException {
		
		Optional<CurrentUserSession> cusers = ulRepo.findById(userId);
		
		if(cusers.isPresent())
		{
			Optional<Product> pro1 = pRepo.findById(productId);
			if(pro1.isPresent())
			{
				Cart cart = new Cart();
				
				cart.getProducts().add(pro1.get());
				cart.setTotalItems(cart.getProducts().size());
				
				int totalPrice = 0;
				for(Product p:cart.getProducts()) totalPrice += p.getPrice();
				
				cart.setTotalPrice(totalPrice);
				
				cRepo.save(cart);
				
				return cart;
			}
			else throw new ProductException("Product does not exist with product Id "+productId);
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
	}

	

	
	
	

}
