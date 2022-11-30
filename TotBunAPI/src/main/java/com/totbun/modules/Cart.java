package com.totbun.modules;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Cart {
	
	@Id
	private Integer cartId;
	private Integer totalItems;
	private Integer totalPrice;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	private List<Product> products = new ArrayList<>();

}
