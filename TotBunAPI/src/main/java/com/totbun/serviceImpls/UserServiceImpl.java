package com.totbun.serviceImpls;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.totbun.exceptions.CartException;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.PaymentException;
import com.totbun.exceptions.ProductException;
import com.totbun.exceptions.UserException;
import com.totbun.modules.Cart;
import com.totbun.modules.CurrentUserSession;
import com.totbun.modules.Order;
import com.totbun.modules.OrderHistory;
import com.totbun.modules.Product;
import com.totbun.modules.Sales;
import com.totbun.modules.User;
import com.totbun.repositories.CartRepo;
import com.totbun.repositories.OrderHistoryRepo;
import com.totbun.repositories.ProductRepo;
import com.totbun.repositories.SalesRepo;
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
	
	@Autowired
	private OrderHistoryRepo ohRepo;
	
	@Autowired
	private SalesRepo sRepo;
	

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
	public User viewPersonalDetails(Integer userId) throws UserException, LogException {
		
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
				Optional<Cart> cart1 = cRepo.findById(11111);
				
				if(cart1.isPresent()) 
				{
					Cart cart = cart1.get();
					cart.getProducts().add(pro1.get());
					cart.setTotalItems(cart.getProducts().size());
					
					int totalPrice = 0;
					for(Product p:cart.getProducts()) totalPrice += p.getPrice();
					cart.setTotalPrice(totalPrice);
					
					return cRepo.save(cart);
					
				}
				else
				{
					Cart cart = new Cart();
					cart.setCartId(11111);
					cart.getProducts().add(pro1.get());
					cart.setTotalItems(cart.getProducts().size());
					
					int totalPrice = 0;
					for(Product p:cart.getProducts()) totalPrice += p.getPrice();
					cart.setTotalPrice(totalPrice);
					
					return cRepo.save(cart);
				}
				
			}
			else throw new ProductException("Product does not exist with product Id "+productId);
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
	}

	@Override
	public String placeOrder(Integer userId, String paymentMethod) throws LogException, CartException, PaymentException {
		
		Optional<CurrentUserSession> cusers = ulRepo.findById(userId);
		
		if(cusers.isPresent())
		{
			Optional<User> user = uRepo.findById(userId);
			
			Optional<Cart> cart = cRepo.findById(11111);
			if(cart.isPresent())
			{
				Cart cart1 = cart.get();
				
				Order order1 = new Order();
				
				order1.setQuantity(cart1.getProducts().size());
				order1.setDateTime(LocalDateTime.now());
				
				if(paymentMethod.equals("Online") || paymentMethod.equals("COD")) 
				{
					order1.setPaymentMethod(paymentMethod);
				}
				else
					throw new PaymentException("Payment method should be Online or COD only...");
				
				order1.setOrderStatus("Out for Delivery");
				order1.setUser(user.get());
				order1.setProducts(cart1.getProducts());
				
				Optional<OrderHistory> orderHistory = ohRepo.findById(userId);
				if(orderHistory.isPresent())
				{
					OrderHistory oh1 = orderHistory.get();
					oh1.getOrders().add(order1);
					ohRepo.save(oh1);
				}
				else
				{
					OrderHistory oh1 = new OrderHistory();
					oh1.setUserId(userId);
					oh1.getOrders().add(order1);
					ohRepo.save(oh1);
				}
				
				cRepo.deleteById(11111);
				
				Sales sales1 = new Sales();
				sales1.setUserId(userId);
				sales1.setOrder(order1);
				sRepo.save(sales1);
				
				return "Order placed...";
			}
			else
				throw new CartException("Cart is empty...");
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
	}

	@Override
	public List<Order> seeOrderHistory(Integer userId) throws LogException, OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
	

}
