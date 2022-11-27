package com.totbun.services;

import com.totbun.exceptions.LogException;
import com.totbun.exceptions.UserException;
import com.totbun.modules.User;

public interface UserService {
	
	public User registerUser(User user) throws UserException, LogException;
	
	public User updateUser(User user) throws UserException, LogException;
	
	public User deleteUser(Integer userId) throws UserException, LogException;
	
	public User viewUser(Integer userId) throws UserException, LogException;
	
}
