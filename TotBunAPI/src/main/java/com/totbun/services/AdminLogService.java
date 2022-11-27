package com.totbun.services;

import com.totbun.DTOs.AdminLoginDTO;
import com.totbun.exceptions.LogException;

public interface AdminLogService {
	
	public String adminLogIn(AdminLoginDTO dto)throws LogException;

	public String adminLogOut(Integer adminId)throws LogException;
}
