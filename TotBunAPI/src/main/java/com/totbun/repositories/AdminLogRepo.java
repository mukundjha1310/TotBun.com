package com.totbun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.totbun.modules.CurrentAdminSession;

public interface AdminLogRepo extends JpaRepository<CurrentAdminSession, Integer>{
	
	
}
