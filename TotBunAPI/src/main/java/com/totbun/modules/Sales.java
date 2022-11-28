package com.totbun.modules;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Sales {
	
	private Integer userId;
	private Integer orderId;
	private Integer productId;
	private Integer productQuantity;
}
