package com.totbun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.totbun.modules.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	
	
}
