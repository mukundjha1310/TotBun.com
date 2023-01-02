package com.totbun.serviceImpls;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.totbun.exceptions.ProductException;
import com.totbun.exceptions.CustomerException;
import com.totbun.modules.Product;
import com.totbun.modules.Customer;
import com.totbun.repositories.CustomerRepo;
import com.totbun.repositories.ProductRepo;
import com.totbun.services.AdminServices;

@Service
public class AdminServicesImpl implements AdminServices {

	@Autowired
	private ProductRepo pRepo;

	@Autowired
	private CustomerRepo cRepo;

	@Override
	public Product addNewProduct(Product product) throws ProductException {

		if (product != null) {
			return pRepo.save(product);
		} else
			throw new ProductException("Invalid product details...");

	}

	@Override
	public Product updateProductQuantity(Integer productId, Integer productQty) throws ProductException {

		Optional<Product> productOpt = pRepo.findById(productId);
		
		if (productOpt.isPresent()) {
			Product p1 = productOpt.get();
			p1.setQuantity(productQty);
			return p1;
		} else
			throw new ProductException("Product not found...");

	}

	@Override
	public List<Customer> viewAllCustomers() throws CustomerException {

		List<Customer> users = cRepo.findAll();
		if (users.size() != 0) {
			return users;
		} else
			throw new CustomerException("No users found...");

	}

	@Override
	public Customer viewCustomerById(Integer customerId) throws CustomerException {

		Optional<Customer> user1 = cRepo.findById(customerId);

		if (user1.isPresent())
			return user1.get();
		else
			throw new CustomerException("User does not exist with user Id " + user1.get().getCustomerId());

	}

	@Override
	public Product seeIndividualProductDetails(Integer productId) throws ProductException {

		Optional<Product> product = pRepo.findById(productId);

		if (product.isPresent())
			return product.get();
		else
			throw new ProductException("Product does not exist with product Id " + productId);

	}

	@Override
	public List<Product> seeAllProductsDetails() throws ProductException {

		List<Product> products = pRepo.findAll();

		if (!products.isEmpty())
			return products;
		else
			throw new ProductException("No products found...");

	}

}
