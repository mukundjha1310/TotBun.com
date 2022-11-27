package com.totbun.services;

import com.totbun.DTOs.UserLoginDTO;
import com.totbun.exceptions.LogException;

public interface UserLogService {
	
	public String userLogIn(UserLoginDTO dto)throws LogException;

	public String userLogOut(Integer userId)throws LogException;

}
