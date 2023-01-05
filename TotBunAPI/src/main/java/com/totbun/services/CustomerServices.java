package com.totbun.services;

import com.totbun.DTOs.RegisterCustomerDTO;
import com.totbun.exceptions.CustomerException;
import com.totbun.modules.Customer;

public interface CustomerServices {

	public String registerCustomer(RegisterCustomerDTO customerDetails) throws CustomerException;

	public Customer updateCustomer(Customer customer) throws CustomerException;

	public Customer deleteCustomer(Integer customerId) throws CustomerException;

	public Customer viewPersonalDetails() throws CustomerException;

}
