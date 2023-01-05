package com.totbun.serviceImpls;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.totbun.DTOs.OrderDTO;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.CustomerException;
import com.totbun.modules.Orders;
import com.totbun.modules.Sales;
import com.totbun.modules.Customer;
import com.totbun.repositories.CustomerRepo;
import com.totbun.repositories.OrderRepo;
import com.totbun.repositories.SalesRepo;
import com.totbun.services.OrderServiceAdmin;

@Service
public class OrderServiceImplAdmin implements OrderServiceAdmin {

	@Autowired
	private OrderRepo oRepo;

	@Autowired
	private CustomerRepo cRepo;

	@Autowired
	private SalesRepo sRepo;

	@Override
	public List<Orders> seeAllOrdersDetails() throws OrderException {

		List<Orders> orders = oRepo.findAll();

		if (!orders.isEmpty())
			return orders;
		else
			throw new OrderException("No Orders found...");

	}

	@Override
	public OrderDTO updateOrderStatus(Integer orderId, String newOrderStatus) throws OrderException {

		Optional<Orders> orders = oRepo.findById(orderId);

		if (orders.isPresent()) {
			Orders o = orders.get();

			if(newOrderStatus.equals("Delivered")) {
				Sales sales = new Sales();
				sales.setUser(o.getCustomer());
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

			return new OrderDTO(orderId, res.getCustomer().getCustomerId(), res.getProduct().getProductId(),
					res.getQuantity(), res.getTotalPrice(), res.getOrderDate(), newOrderStatus);
		} else
			throw new OrderException("No Order found with this order Id " + orderId);

	}

	@Override
	public List<Orders> searchOrdersByCustomerId(Integer customerId) throws OrderException, CustomerException {

		Optional<Customer> customer = cRepo.findById(customerId);

		if (customer.isPresent()) 
		{
			Customer cust = customer.get();
			List<Orders> orders = oRepo.findByCustomer(cust);

			if (!orders.isEmpty())
				return orders;
			else
				throw new OrderException("No Orders placed by the user " + cust.getFirstName());
		} else
			throw new CustomerException("Invalid User Id...");

	}

}
