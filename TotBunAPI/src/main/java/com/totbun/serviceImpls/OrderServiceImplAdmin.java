package com.totbun.serviceImpls;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totbun.DTOs.OrderDTO;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.UserException;
import com.totbun.modules.CurrentAdminSession;
import com.totbun.modules.Orders;
import com.totbun.modules.Sales;
import com.totbun.modules.User;
import com.totbun.repositories.AdminLogRepo;
import com.totbun.repositories.OrderRepo;
import com.totbun.repositories.SalesRepo;
import com.totbun.repositories.UserRepo;
import com.totbun.services.OrderServiceAdmin;

@Service
public class OrderServiceImplAdmin implements OrderServiceAdmin{
	
	@Autowired
	private AdminLogRepo alRepo;
	
	@Autowired
	private OrderRepo oRepo;
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private SalesRepo sRepo;

	@Override
	public List<Orders> seeAllOrdersDetails(Integer adminId) throws LogException, OrderException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			List<Orders> orders = oRepo.findAll();
			
			if(!orders.isEmpty()) 
				return orders;
			else
				throw new OrderException("No Orders found...");
		}
		else throw new LogException("Please Login as admin first...");
	}

	@Override
	public OrderDTO updateOrderStatus(Integer adminId, Integer orderId, String newOrderStatus)
			throws LogException, OrderException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			Optional<Orders> orders = oRepo.findById(orderId);
			
			if(orders.isPresent())
			{
				Orders o = orders.get();
				
				if(newOrderStatus.equals("Delivered"))
				{
					Sales sales = new Sales();
					sales.setUser(o.getUser());
					sales.setProduct(o.getProduct());
					sales.setQuantity(o.getQuantity());
					sales.setTotalPrice(o.getTotalPrice());
					sales.setDeliveryDate(LocalDate.now());
					sales.setPaymentType(o.getPaymentType());
					sales.setOrderStatus(newOrderStatus);
					sRepo.save(sales);
				}
				
				o.setOrderStatus(newOrderStatus);
				Orders res = oRepo.save(o);
				
				return new OrderDTO(orderId, res.getUser().getUserId(), res.getProduct().getProductId(), 
						res.getQuantity(), res.getTotalPrice(), res.getOrderDate(), newOrderStatus);
			}
			else
				throw new OrderException("No Order found with this order Id "+orderId);
		}
		else throw new LogException("Please Login as admin first...");
	}

	@Override
	public List<Orders> searchOrdersByUserId(Integer adminId, Integer userId)
			throws LogException, OrderException, UserException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			Optional<User> user = uRepo.findById(userId);
			
			if(user.isPresent())
			{
				List<Orders> orders = oRepo.findByUser(user.get());
				
				if(!orders.isEmpty())
					return orders;
				else
					throw new OrderException("No Orders placed by the user "+user.get().getFirstName());
			}
			else throw new UserException("Invalid User Id...");
		}
		else throw new LogException("Please Login as admin first...");
	}

}
