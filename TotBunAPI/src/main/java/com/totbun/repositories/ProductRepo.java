package com.totbun.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.totbun.modules.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	//@Query("from product where product_title like :productName")
	
	public List<Product> findByProductTitleContainingIgnoreCase(String productName);
	
}
