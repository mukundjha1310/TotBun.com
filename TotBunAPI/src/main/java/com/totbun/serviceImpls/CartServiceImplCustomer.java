package com.totbun.serviceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.totbun.DTOs.CartAllDetailsDTO;
import com.totbun.DTOs.ProductDTO;
import com.totbun.exceptions.CartException;
import com.totbun.exceptions.ProductException;
import com.totbun.modules.Cart;
import com.totbun.modules.Customer;
import com.totbun.modules.Product;
import com.totbun.repositories.CartRepo;
import com.totbun.repositories.CustomerRepo;
import com.totbun.repositories.ProductRepo;
import com.totbun.services.CartServicesCustomer;

@Service
public class CartServiceImplCustomer implements CartServicesCustomer {

	@Autowired
	private CustomerRepo custRepo;

	@Autowired
	private ProductRepo pRepo;

	@Autowired
	private CartRepo cRepo;

	@Override
	public String addProductToCart(Integer productId, Integer quantity) throws ProductException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Optional<Customer> customer = custRepo.findByEmailId(authentication.getName());

		Optional<Product> product = pRepo.findById(productId);

		if (product.isPresent()) {
			Product product1 = product.get();
			Cart cart = new Cart();
			cart.setCustomer(customer.get());
			cart.setProduct(product1);
			cart.setQuantity(quantity);
			cart.setTotalPrice(quantity * product1.getPrice());

			cRepo.save(cart);

			return "Product Added to cart...";

		} else
			throw new ProductException("Product does not exist with product Id " + productId);

	}

	@Override
	public CartAllDetailsDTO seeCartDetails() throws CartException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Optional<Customer> customer = custRepo.findByEmailId(authentication.getName());

		List<Cart> cartDetails = cRepo.findByCustomerId(customer.get().getCustomerId());

		CartAllDetailsDTO result = new CartAllDetailsDTO();

		List<ProductDTO> productsInCart = new ArrayList<>();

		int totalProducts = 0;
		int totalCartPrice = 0;

		if (cartDetails.size() == 0)
			throw new CartException("Cart is empty...");
		else {
			for (Cart c : cartDetails) {
				Product prod = c.getProduct();

				ProductDTO cartProduct = new ProductDTO(prod.getProductId(), prod.getProductTitle(), prod.getImage(),
						c.getTotalPrice(), c.getQuantity(), prod.getProductRating(), prod.getProductCategory(),
						prod.getBrand());

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

	@Override
	public String deleteProductFromCart(Integer productId) throws CartException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Optional<Customer> customer = custRepo.findByEmailId(authentication.getName());

		Cart cart = cRepo.findCartByProductId(customer.get().getCustomerId(), productId);

		if (cart != null) 
		{
			cRepo.delete(cart);
			return "Product deleted from cart...";
		}
		else
			throw new CartException("Product does not exist in cart with product Id " + productId);

	}

}
