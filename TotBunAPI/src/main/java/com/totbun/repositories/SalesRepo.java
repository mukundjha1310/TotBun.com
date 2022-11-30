package com.totbun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.totbun.modules.Sales;

@Repository
public interface SalesRepo extends JpaRepository<Sales, Integer>{
	
	
}
