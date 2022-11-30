package com.totbun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.totbun.modules.OrderHistory;

@Repository
public interface OrderHistoryRepo extends JpaRepository<OrderHistory, Integer>{
	
	
}
