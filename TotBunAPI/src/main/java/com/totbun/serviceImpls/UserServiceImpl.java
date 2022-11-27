package com.totbun.serviceImpls;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.UserException;
import com.totbun.modules.CurrentUserSession;
import com.totbun.modules.User;
import com.totbun.repositories.AdminLogRepo;
import com.totbun.repositories.UserLogRepo;
import com.totbun.repositories.UserRepo;
import com.totbun.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private UserLogRepo ulRepo;
	

	@Override
	public User registerUser(User user) throws UserException, LogException {
		
		User user1 = uRepo.findByEmailId(user.getEmailId());
		
		if(user1 == null)
			return uRepo.save(user);
		else
			throw new UserException("User already exist with email Id "+user.getEmailId());
	}

	@Override
	public User updateUser(User user) throws UserException, LogException {
		
		Optional<CurrentUserSession> cusess = ulRepo.findById(user.getUserId());
		
		if(cusess.isPresent())
		{
			Optional<User> user1 = uRepo.findById(user.getUserId());
			
			if(user1.isPresent())
				return uRepo.save(user);
			else
				throw new UserException("User does not exist with user Id "+user.getUserId());
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
		
		
	}

	@Override
	public User deleteUser(Integer userId) throws UserException, LogException {
		
		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent())
		{
			Optional<User> user1 = uRepo.findById(userId);
			
			if(user1.isPresent()) 
			{
				uRepo.deleteById(userId);
				ulRepo.deleteById(userId);
				return user1.get();
			}
			else
				throw new UserException("User does not exist with user Id "+user1.get().getUserId());
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
		
		
	}

	@Override
	public User viewUser(Integer userId) throws UserException, LogException {
		
		Optional<User> user1 = uRepo.findById(userId);
		
		if(user1.isPresent()) 
			return user1.get();
		else
			throw new UserException("User does not exist with user Id "+user1.get().getUserId());
	}

	
	
	

}
