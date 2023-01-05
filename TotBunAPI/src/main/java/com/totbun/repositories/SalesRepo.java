package com.totbun.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.totbun.modules.Sales;

@Repository
public interface SalesRepo extends JpaRepository<Sales, Integer>{
	
	List<Sales> findByDeliveryDate(LocalDate deliveryDate);

	List<Sales> findByDeliveryDateBetween(LocalDate from, LocalDate to);
}
