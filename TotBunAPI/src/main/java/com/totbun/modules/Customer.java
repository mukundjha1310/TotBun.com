package com.totbun.modules;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
	@SequenceGenerator(name = "customer_seq", sequenceName = "Customer_Id", allocationSize=1, initialValue=101)
	private Integer customerId;
	
	@NotNull(message = "First name field is mandatory.")
	@Size(min = 2, max = 15, message = "FirstName should not be less than size 2 and more than 15.")
	private String firstName;
	
	private String lastName;
	
	@NotNull(message = "Mobile number field is mandatory.")
	@Pattern(regexp = "\\d{10}", message = "Mobile number should not be less than or more than 10 digits.")
	private String mobile;
	
	@NotNull(message = "Email Id field is mandatory.")
	@Email(message = "Invalid Email address..!")
	private String emailId;
	
	@JsonIgnore
	@NotNull(message = "Password field is mandatory.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&])(?=\\S+$).{8,20}$", message = "Password must contain at least one digit [0-9], one lowercase character [a-z], one uppercase character [A-Z], one special character like [@#$%&] and a length of at least 8 characters, and a maximum of 20 characters with no space.")
	private String password;
	
	@NotNull(message = "Address field is mandatory.")
	@Embedded
	private CustomerAddress customerAddress;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Customer_Roles", 
	joinColumns = @JoinColumn(name = "Customer_Id"), 
	inverseJoinColumns = @JoinColumn(name = "Role_Id"))
	private Set<UserRole> roles = new HashSet<>();
	
	
}
