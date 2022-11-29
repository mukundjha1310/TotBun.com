package com.totbun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.totbun.modules.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	public User findByEmailId(String emailId);
	
}
