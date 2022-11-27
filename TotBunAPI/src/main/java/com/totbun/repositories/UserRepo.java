package com.totbun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.totbun.modules.User;


public interface UserRepo extends JpaRepository<User, Integer>{
	
	public User findByEmailId(String emailId);
	
}
