package com.totbun.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.totbun.modules.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	
	@Query("from Cart where customer_id = ?1")
	public List<Cart> findByCustomerId(Integer customerId);
	
	@Query("from Cart where customer_id = ?1 and  product_id= ?2")
	public Cart findCartByProductId(Integer customerId, Integer productId);
}
