package com.totbun.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.totbun.modules.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer>{
	
	@Query("from Orders where user_id = ?1")
	public List<Orders> searchOrders(Integer userId);
}
