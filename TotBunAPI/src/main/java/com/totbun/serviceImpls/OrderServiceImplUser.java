package com.totbun.serviceImpls;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.totbun.DTOs.OrderHistoryDTO;
import com.totbun.DTOs.ProductDTO;
import com.totbun.exceptions.CartException;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.PaymentException;
import com.totbun.modules.Cart;
import com.totbun.modules.CurrentUserSession;
import com.totbun.modules.Orders;
import com.totbun.modules.Product;
import com.totbun.modules.User;
import com.totbun.repositories.CartRepo;
import com.totbun.repositories.OrderRepo;
import com.totbun.repositories.ProductRepo;
import com.totbun.repositories.UserLogRepo;
import com.totbun.repositories.UserRepo;
import com.totbun.services.OrderServiceUser;

@Service
public class OrderServiceImplUser implements OrderServiceUser{
	
	@Autowired
	private ProductRepo pRepo;
	
	@Autowired
	private CartRepo cRepo;
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private UserLogRepo ulRepo;
	
	@Autowired
	private OrderRepo oRepo;

	@Override
	public String placeOrder(Integer userId, String paymentType) throws LogException, CartException, PaymentException {
		
		Optional<CurrentUserSession> cuser = ulRepo.findById(userId);
		
		if(cuser.isPresent())
		{
			if(paymentType.equals("Cash On Delivery") || paymentType.equals("Card") || paymentType.equals("Net Banking"))
			{
				List<Cart> cartProducts = cRepo.findByUser(userId);
				
				if(cartProducts.size() == 0)
					throw new CartException("Cart is empty...");
				else
				{
					for(Cart c:cartProducts)
					{
						Product product = c.getProduct();
						product.setQuantity(product.getQuantity()-c.getQuantity());
						pRepo.save(product);
						
						Orders order = new Orders();
						
						order.setUser(c.getUser());
						order.setProduct(c.getProduct());
						order.setQuantity(c.getQuantity());
						order.setTotalPrice(c.getTotalPrice());
						order.setOrderDate(LocalDate.now());
						order.setPaymentType(paymentType);
						order.setOrderStatus("Preparing for dispatch order.");
						
						oRepo.save(order);
						
						cRepo.delete(c);
					}
						
					return "Order placed successfully...";
				}
			}
			else
				throw new PaymentException("Payment type can be Cash On Delivery or Card or Net Banking only...");
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
	}

	@Override
	public List<OrderHistoryDTO> seeOrderHistory(Integer userId) throws LogException, OrderException {
		
		Optional<CurrentUserSession> cuser = ulRepo.findById(userId);
		
		if(cuser.isPresent())
		{
			@SuppressWarnings("deprecation")
			User user = uRepo.getById(userId);
		
			List<Orders> orders = oRepo.findByUser(user);
			
			if(orders.size() == 0)
				throw new OrderException("No previous orders found...");
			else
			{
				List<OrderHistoryDTO> orderHistories = new ArrayList<>();
				
				for(Orders o:orders)
				{
					Product p1 = o.getProduct();
					ProductDTO product = new ProductDTO(p1.getProductId(), p1.getProductTitle(), 
							p1.getImage(), p1.getPrice(), o.getQuantity(), p1.getProductRating(),  
							p1.getProductCategory(), p1.getBrand());
					OrderHistoryDTO orderHistory = new OrderHistoryDTO(product, o.getOrderDate(), 
							o.getPaymentType(), o.getOrderStatus());
					
					orderHistories.add(orderHistory);
				}
				
				return orderHistories;
			}
			
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
	}

}
