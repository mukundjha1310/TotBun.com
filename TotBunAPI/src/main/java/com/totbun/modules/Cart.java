package com.totbun.modules;

import java.util.List;
import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Cart {
	private Integer totalItems;
	private Integer totalPrice;
	
	private List<Product> products;
}
