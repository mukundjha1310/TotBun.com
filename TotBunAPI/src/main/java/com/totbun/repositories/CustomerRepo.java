package com.totbun.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.totbun.modules.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	
	Optional<Customer> findByEmailId(String emailId);
	
}
