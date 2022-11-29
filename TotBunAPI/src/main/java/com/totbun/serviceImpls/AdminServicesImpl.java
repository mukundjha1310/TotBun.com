package com.totbun.serviceImpls;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.ProductException;
import com.totbun.exceptions.UserException;
import com.totbun.modules.CurrentAdminSession;
import com.totbun.modules.Product;
import com.totbun.modules.User;
import com.totbun.repositories.AdminLogRepo;
import com.totbun.repositories.AdminRepo;
import com.totbun.repositories.ProductRepo;
import com.totbun.repositories.UserRepo;
import com.totbun.services.AdminServices;

@Service
public class AdminServicesImpl implements AdminServices{
	
	@Autowired
	private ProductRepo pRepo;
	
	@Autowired
	private AdminRepo aRepo;
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private AdminLogRepo alRepo;

	@Override
	public Product addNewProduct(Integer adminId, Product product) throws LogException, ProductException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			if(product != null)
			{
				return pRepo.save(product);
			}
			else throw new ProductException("Invalid product details...");
		}
		else throw new LogException("Please Login as admin first...");
	}

	@Override
	public Product updateProductQuantity(Integer adminId, Integer productId, Integer productQty) throws LogException, ProductException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			Optional<Product> productOpt = pRepo.findById(productId);
			if(productOpt.isPresent())
			{
				Product p1 = productOpt.get();
				p1.setQuantity(productQty);
				return p1;
			}
			else throw new ProductException("Product not found...");
		}
		else throw new LogException("Please Login as admin first...");
	}

	@Override
	public List<User> viewAllUsers(Integer adminId) throws UserException, LogException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			List<User> users = uRepo.findAll();
			if(users.size() != 0)
			{
				return users;
			}
			else throw new UserException("No users found...");
		}
		else throw new LogException("Please Login as admin first...");
	}

}
