package com.totbun.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.totbun.modules.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	public List<Product> findByProductTitleContainingIgnoreCase(String productName);
	
	public List<Product> findByProductCategoryContainingIgnoreCase(String category);
	
}
