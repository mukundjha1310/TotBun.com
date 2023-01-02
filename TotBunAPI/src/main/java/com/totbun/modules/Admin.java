package com.totbun.modules;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Admin_Roles", 
	joinColumns = @JoinColumn(name = "Admin_Id"), 
	inverseJoinColumns = @JoinColumn(name = "Role_Id"))
	private Set<UserRole> roles = new HashSet<>();
	
}
