package com.totbun.modules;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class OrderHistory {
	
	@Id
	private Integer userId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orderHistory")
	private List<Order> orders = new ArrayList<>();
	
	
}
