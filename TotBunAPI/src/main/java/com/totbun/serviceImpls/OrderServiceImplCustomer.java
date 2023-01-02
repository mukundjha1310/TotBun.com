package com.totbun.serviceImpls;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.totbun.DTOs.OrderHistoryDTO;
import com.totbun.DTOs.ProductDTO;
import com.totbun.exceptions.CartException;
import com.totbun.exceptions.OrderException;
import com.totbun.exceptions.PaymentException;
import com.totbun.modules.Cart;
import com.totbun.modules.Orders;
import com.totbun.modules.Product;
import com.totbun.modules.Customer;
import com.totbun.repositories.CartRepo;
import com.totbun.repositories.CustomerRepo;
import com.totbun.repositories.OrderRepo;
import com.totbun.repositories.ProductRepo;
import com.totbun.services.OrderServiceCustomer;

@Service
public class OrderServiceImplCustomer implements OrderServiceCustomer {

	@Autowired
	private ProductRepo pRepo;

	@Autowired
	private CartRepo cRepo;

	@Autowired
	private CustomerRepo custRepo;

	@Autowired
	private OrderRepo oRepo;

	@Override
	public String placeOrder(String paymentType) throws CartException, PaymentException {

		if (paymentType.equals("Cash On Delivery") || paymentType.equals("Card") || paymentType.equals("Net Banking")) {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			Optional<Customer> customer = custRepo.findByEmailId(authentication.getName());
			
			List<Cart> cartProducts = cRepo.findByCustomerId(customer.get().getCustomerId());

			if (cartProducts.size() == 0)
				throw new CartException("Cart is empty..!");
			else {
				for (Cart c : cartProducts) {
					Product product = c.getProduct();
					product.setQuantity(product.getQuantity() - c.getQuantity());
					pRepo.save(product);

					Orders order = new Orders();

					order.setCustomer(c.getCustomer());
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
		} else
			throw new PaymentException("Payment type can be Cash On Delivery or Card or Net Banking only...");

	}

	@Override
	public List<OrderHistoryDTO> seeOrderHistory() throws OrderException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Optional<Customer> customer = custRepo.findByEmailId(authentication.getName());
		
		List<Orders> orders = oRepo.findByCustomer(customer.get());

		if (orders.size() == 0)
			throw new OrderException("No previous orders found..!");
		else {
			List<OrderHistoryDTO> orderHistories = new ArrayList<>();

			for (Orders o : orders) {
				Product p1 = o.getProduct();
				ProductDTO product = new ProductDTO(p1.getProductId(), p1.getProductTitle(), p1.getImage(),
						p1.getPrice(), o.getQuantity(), p1.getProductRating(), p1.getProductCategory(), p1.getBrand());
				OrderHistoryDTO orderHistory = new OrderHistoryDTO(product, o.getOrderDate(), o.getPaymentType(),
						o.getOrderStatus());

				orderHistories.add(orderHistory);
			}

			return orderHistories;
		}

	}

}
