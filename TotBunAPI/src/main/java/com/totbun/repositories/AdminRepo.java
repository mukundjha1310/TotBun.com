package com.totbun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.totbun.modules.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	public Admin findByAdminUserName(String adminUserName);
}
