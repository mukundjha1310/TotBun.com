package com.totbun.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
	
	private Integer flatOrHouseNo;
	private String areaOrStreet;
	private String townOrCity;
	private String pincode;
	private String state;
	private String country;
	
}
