package com.totbun.modules;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer salesId;
	private Integer userId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Order order;
}
