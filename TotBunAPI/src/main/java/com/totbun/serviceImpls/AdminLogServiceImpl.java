package com.totbun.serviceImpls;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.totbun.DTOs.AdminLoginDTO;
import com.totbun.exceptions.LogException;
import com.totbun.modules.Admin;
import com.totbun.modules.CurrentAdminSession;
import com.totbun.repositories.AdminLogRepo;
import com.totbun.repositories.AdminRepo;
import com.totbun.services.AdminLogService;

@Service
public class AdminLogServiceImpl implements AdminLogService {

	@Autowired
	private AdminRepo aRepo;
	
	@Autowired
	private AdminLogRepo adminLogRepo;
	
	@Override
	public String adminLogIn(AdminLoginDTO dto) throws LogException {
		
		Admin admin = aRepo.findByAdminUserName(dto.getAdminUserName());
		
		if(admin == null) throw new LogException("Please Enter a valid admin UserName.");
		
		if(admin.getAdminPassword().equals(dto.getPassword()))
		{
			Optional<CurrentAdminSession> cAdminSession = adminLogRepo.findById(admin.getAdminId());
			
			if(cAdminSession.isPresent()) throw new LogException("You are already LoggedIn with this UserName "+dto.getAdminUserName());
			
			adminLogRepo.save(new CurrentAdminSession(admin.getAdminId(), "Admin", LocalDateTime.now()));
			
			return dto.getAdminUserName()+" you are loggedIn successfully.";
		}
		else
			throw new LogException("Please Enter a valid User name or Password.");
	}

	@Override
	public String adminLogOut(Integer adminId) throws LogException {
		
		Optional<CurrentAdminSession> cAdminSession = adminLogRepo.findById(adminId);
		
		if(cAdminSession.isPresent())
		{
			adminLogRepo.deleteById(adminId);
			
			return "You are successfully logged Out.";
		}
		else
			throw new LogException("Please provide a valid Id.");
	}

	

}
