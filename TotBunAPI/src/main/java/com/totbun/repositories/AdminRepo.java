package com.totbun.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.totbun.modules.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	Optional<Admin> findByEmailId(String emailId);
}
