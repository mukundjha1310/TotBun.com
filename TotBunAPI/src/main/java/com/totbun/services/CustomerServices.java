package com.totbun.services;

import com.totbun.DTOs.RegisterCustomerDTO;
import com.totbun.exceptions.CustomerException;
import com.totbun.modules.Customer;

public interface CustomerServices {

	String registerCustomer(RegisterCustomerDTO customerDetails) throws CustomerException;

	Customer updateCustomer(Customer customer) throws CustomerException;

	Customer deleteCustomer(Integer customerId) throws CustomerException;

	Customer viewPersonalDetails() throws CustomerException;

}
