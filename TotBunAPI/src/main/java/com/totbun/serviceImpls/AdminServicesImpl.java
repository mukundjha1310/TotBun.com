package com.totbun.serviceImpls;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.totbun.exceptions.ProductException;
import com.totbun.DTOs.AdminRegisterDTO;
import com.totbun.DTOs.CustomerDTO;
import com.totbun.exceptions.AdminException;
import com.totbun.exceptions.CustomerException;
import com.totbun.modules.Product;
import com.totbun.modules.Role;
import com.totbun.modules.User;
import com.totbun.modules.UserRole;
import com.totbun.modules.Admin;
import com.totbun.modules.Customer;
import com.totbun.repositories.AdminRepo;
import com.totbun.repositories.CustomerRepo;
import com.totbun.repositories.ProductRepo;
import com.totbun.repositories.UserRepo;
import com.totbun.repositories.UserRoleRepo;
import com.totbun.services.AdminServices;

@Service
public class AdminServicesImpl implements AdminServices {

	@Autowired
	private ProductRepo pRepo;

	@Autowired
	private CustomerRepo cRepo;
	
	@Autowired
	private AdminRepo aRepo;
	
	@Autowired
	private UserRoleRepo urRepo;
	
	@Autowired
	private UserRepo uRepo;

	@Override
	public String registerAdmin(AdminRegisterDTO adminDetails) throws AdminException {
		
		Optional<Admin> admin0 = aRepo.findByEmailId(adminDetails.getEmailId());

		if (admin0.isEmpty()) {

			Set<String> strRoles = adminDetails.getRoles();

			Set<UserRole> roles = new HashSet<>();

			if (strRoles == null) {
				UserRole adminRole = urRepo.findByUserRole(Role.ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Customer Role is not found..!"));
				roles.add(adminRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "admin":
						UserRole adminRole = urRepo.findByUserRole(Role.ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: admin Role is not found."));
						roles.add(adminRole);

						break;
					case "customer":
						UserRole studentRole = urRepo.findByUserRole(Role.CUSTOMER)
								.orElseThrow(() -> new RuntimeException("Error: customer Role is not found."));
						roles.add(studentRole);
						break;
					}
				});
			}

			User user = new User();
			user.setName(adminDetails.getName());
			user.setEmailId(adminDetails.getEmailId());
			user.setPassword(adminDetails.getPassword());
			user.setRoles(roles);
			uRepo.save(user);

			Admin admin = new Admin();
			admin.setName(adminDetails.getName());
			admin.setMobile(adminDetails.getMobile());
			admin.setEmailId(adminDetails.getEmailId());
			admin.setPassword(adminDetails.getPassword());
			admin.setRoles(roles);
			
			aRepo.save(admin);

			return adminDetails.getName() + " you are registered successfully...";

		} else
			throw new AdminException("Admin already exist with this emailId..!");
	}

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
	public List<CustomerDTO> viewAllCustomers() throws CustomerException {

		List<Customer> customers = cRepo.findAll();
		
		List<CustomerDTO> custDTO = new ArrayList<>();
		
		if (customers.size() != 0) {
			
			for(Customer c:customers)
				custDTO.add(new CustomerDTO(c.getCustomerId(), c.getFirstName(), c.getEmailId()));
			
			return custDTO;
			
		} else
			throw new CustomerException("No customers found..!");

	}

	@Override
	public Customer viewCustomerById(Integer customerId) throws CustomerException {

		Optional<Customer> user1 = cRepo.findById(customerId);

		if (user1.isPresent())
			return user1.get();
		else
			throw new CustomerException("Customer does not exist with customer Id " + customerId);

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
