package com.totbun.modules;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Admin {

	@Id
	@Column(unique = true)
	private Integer adminId;
	private String adminUserName;
	private String adminPassword;
	
	
	//insert into admin values(4,'MukundJha','mukund1234');
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
