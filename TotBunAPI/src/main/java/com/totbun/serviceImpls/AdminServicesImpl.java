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
import com.totbun.repositories.ProductRepo;
import com.totbun.repositories.UserRepo;
import com.totbun.services.AdminServices;

@Service
public class AdminServicesImpl implements AdminServices{
	
	@Autowired
	private ProductRepo pRepo;
	
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
	
	@Override
	public User viewUserById(Integer adminId, Integer userId) throws UserException, LogException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			Optional<User> user1 = uRepo.findById(userId);
			
			if(user1.isPresent()) 
				return user1.get();
			else
				throw new UserException("User does not exist with user Id "+user1.get().getUserId());
		}
		else throw new LogException("Please Login as admin first...");
		
	}

	@Override
	public Product seeIndividualProductDetails(Integer adminId, Integer productId)
			throws LogException, ProductException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			Optional<Product> product = pRepo.findById(productId);
			
			if(product.isPresent()) 
				return product.get();
			else
				throw new ProductException("Product does not exist with product Id "+productId);
		}
		else throw new LogException("Please Login as admin first...");
	}

	@Override
	public List<Product> seeAllProductsDetails(Integer adminId) throws LogException, ProductException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			List<Product> products = pRepo.findAll();
			
			if(!products.isEmpty()) 
				return products;
			else
				throw new ProductException("No products found...");
		}
		else throw new LogException("Please Login as admin first...");
	}

}
