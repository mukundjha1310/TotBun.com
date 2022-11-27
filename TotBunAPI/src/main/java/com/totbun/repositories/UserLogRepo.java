package com.totbun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.totbun.modules.CurrentUserSession;

public interface UserLogRepo extends JpaRepository<CurrentUserSession, Integer>{
	
	
}
