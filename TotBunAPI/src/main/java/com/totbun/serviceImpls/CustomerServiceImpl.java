package com.totbun.serviceImpls;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.totbun.DTOs.RegisterCustomerDTO;
import com.totbun.exceptions.CustomerException;
import com.totbun.modules.Customer;
import com.totbun.modules.Role;
import com.totbun.modules.User;
import com.totbun.modules.UserRole;
import com.totbun.repositories.CustomerRepo;
import com.totbun.repositories.UserRepo;
import com.totbun.repositories.UserRoleRepo;
import com.totbun.services.CustomerServices;

@Service
public class CustomerServiceImpl implements CustomerServices {

	@Autowired
	private CustomerRepo custRepo;

	@Autowired
	private UserRoleRepo urRepo;

	@Autowired
	private UserRepo uRepo;

	@Override
	public String registerCustomer(RegisterCustomerDTO customerDetails) throws CustomerException {

		Optional<Customer> customer0 = custRepo.findByEmailId(customerDetails.getEmailId());

		if (customer0.isEmpty()) {

			Set<String> strRoles = customerDetails.getRoles();

			Set<UserRole> roles = new HashSet<>();

			if (strRoles == null) {
				UserRole customerRole = urRepo.findByUserRole(Role.CUSTOMER)
						.orElseThrow(() -> new RuntimeException("Error: Customer Role is not found..!"));
				roles.add(customerRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "admin":
						UserRole adminRole = urRepo.findByUserRole(Role.ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Admin Role is not found."));
						roles.add(adminRole);

						break;
					case "customer":
						UserRole studentRole = urRepo.findByUserRole(Role.CUSTOMER)
								.orElseThrow(() -> new RuntimeException("Error: Customer Role is not found."));
						roles.add(studentRole);
						break;
					}
				});
			}

			User user = new User();
			user.setName(customerDetails.getFirstName() + " " + customerDetails.getLastName());
			user.setEmailId(customerDetails.getEmailId());
			user.setPassword(customerDetails.getPassword());
			user.setRoles(roles);
			uRepo.save(user);

			Customer customer = new Customer();
			customer.setFirstName(customerDetails.getFirstName());
			customer.setLastName(customerDetails.getLastName());
			customer.setMobile(customerDetails.getMobile());
			customer.setEmailId(customerDetails.getEmailId());
			customer.setPassword(customerDetails.getPassword());
			customer.setCustomerAddress(customerDetails.getCustomerAddress());
			customer.setRoles(roles);
			
			custRepo.save(customer);

			return customerDetails.getFirstName() + " you are registered successfully...";

		} else
			throw new CustomerException("Customer already exist with this emailId..!");

	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {

		Optional<Customer> user1 = custRepo.findById(customer.getCustomerId());

		if (user1.isPresent())
			return custRepo.save(customer);
		else
			throw new CustomerException("Customer does not exist with customer Id " + customer.getCustomerId());

	}

	@Override
	public Customer deleteCustomer(Integer customerId) throws CustomerException {

		Optional<Customer> user1 = custRepo.findById(customerId);

		if (user1.isPresent()) {
			uRepo.deleteById(customerId);
			return user1.get();
		} else
			throw new CustomerException("Customer does not exist with user Id " + user1.get().getCustomerId());

	}

	@Override
	public Customer viewPersonalDetails() throws CustomerException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Optional<Customer> customer = custRepo.findByEmailId(authentication.getName());
		
		return customer.get();

	}

}
