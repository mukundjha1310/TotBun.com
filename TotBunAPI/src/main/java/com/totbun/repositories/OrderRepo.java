package com.totbun.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.totbun.modules.Orders;
import com.totbun.modules.Customer;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer>{
	
	public List<Orders> findByCustomer(Customer customer);
}
