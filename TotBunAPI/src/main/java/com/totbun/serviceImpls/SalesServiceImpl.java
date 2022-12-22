package com.totbun.serviceImpls;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.totbun.DTOs.ProductDTO;
import com.totbun.DTOs.SalesDTO;
import com.totbun.DTOs.UserDTO;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.SalesException;
import com.totbun.modules.CurrentAdminSession;
import com.totbun.modules.Product;
import com.totbun.modules.Sales;
import com.totbun.modules.User;
import com.totbun.repositories.AdminLogRepo;
import com.totbun.repositories.SalesRepo;
import com.totbun.services.SalesService;

@Service
public class SalesServiceImpl implements SalesService{
	
	@Autowired
	private AdminLogRepo alRepo;
	
	@Autowired
	private SalesRepo sRepo;

	@Override
	public List<SalesDTO> salesMadeToday(Integer adminId) throws LogException, SalesException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			List<Sales> salesList = sRepo.findByDeliveryDate(LocalDate.now());
			
			if(salesList.size() != 0)
			{
				List<SalesDTO> resList = new ArrayList<>();
				
				for(Sales s:salesList)
				{
					User user = s.getUser();
					UserDTO userdto = new UserDTO(user.getUserId(), 
							user.getFirstName()+" "+user.getLastName(), user.getEmailId());
					
					Product p1 = s.getProduct();
					ProductDTO productdto = new ProductDTO(p1.getProductId(), p1.getProductTitle(), 
							p1.getImage(), p1.getPrice(), s.getQuantity(), p1.getProductRating(),  
							p1.getProductCategory(), p1.getBrand());
					
					resList.add(new SalesDTO(s.getSalesId(), userdto, productdto, s.getQuantity(), 
							s.getTotalPrice(), s.getDeliveryDate(), s.getPaymentType(), s.getOrderStatus()));
				}
				
				return resList;
				
			}
			throw new SalesException("No one sales made todays..!");
		}
		else throw new LogException("Please Login as admin first...");
		
	}

	@Override
	public List<SalesDTO> salesMadeLastWeek(Integer adminId) throws LogException, SalesException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			LocalDate now = LocalDate.now();
			LocalDate from = now.minusDays(6+now.getDayOfWeek().getValue());
			LocalDate to = now.minusDays(now.getDayOfWeek().getValue());
			
			List<Sales> salesList = sRepo.findByDeliveryDateBetween(from, to);
			
			if(salesList.size() != 0)
			{
				List<SalesDTO> resList = new ArrayList<>();
				
				for(Sales s:salesList)
				{
					User user = s.getUser();
					UserDTO userdto = new UserDTO(user.getUserId(), 
							user.getFirstName()+" "+user.getLastName(), user.getEmailId());
					
					Product p1 = s.getProduct();
					ProductDTO productdto = new ProductDTO(p1.getProductId(), p1.getProductTitle(), 
							p1.getImage(), p1.getPrice(), s.getQuantity(), p1.getProductRating(),  
							p1.getProductCategory(), p1.getBrand());
					
					resList.add(new SalesDTO(s.getSalesId(), userdto, productdto, s.getQuantity(), 
							s.getTotalPrice(), s.getDeliveryDate(), s.getPaymentType(), s.getOrderStatus()));
				}
				return resList;
			}
			throw new SalesException("No one sales made in last week..!");
		}
		else throw new LogException("Please Login as admin first...");
	}

	@Override
	public List<SalesDTO> salesMadeLastMonth(Integer adminId) throws LogException, SalesException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			LocalDate now = LocalDate.now();
			LocalDate previousMonth = now.minusMonths(1);
			LocalDate monthStart = previousMonth.withDayOfMonth(1);
			LocalDate monthEnd = previousMonth.withDayOfMonth(previousMonth.getMonth().maxLength());
			
			List<Sales> salesList = sRepo.findByDeliveryDateBetween(monthStart, monthEnd);
			
			if(salesList.size() != 0)
			{
				List<SalesDTO> resList = new ArrayList<>();
				
				for(Sales s:salesList)
				{
					User user = s.getUser();
					UserDTO userdto = new UserDTO(user.getUserId(), 
							user.getFirstName()+" "+user.getLastName(), user.getEmailId());
					
					Product p1 = s.getProduct();
					ProductDTO productdto = new ProductDTO(p1.getProductId(), p1.getProductTitle(), 
							p1.getImage(), p1.getPrice(), s.getQuantity(), p1.getProductRating(),  
							p1.getProductCategory(), p1.getBrand());
					
					resList.add(new SalesDTO(s.getSalesId(), userdto, productdto, s.getQuantity(), 
							s.getTotalPrice(), s.getDeliveryDate(), s.getPaymentType(), s.getOrderStatus()));
				}
				return resList;
			}
			throw new SalesException("No one sales made in last month..!");
		}
		else throw new LogException("Please Login as admin first...");
	}

	@Override
	public List<SalesDTO> salesBetweenMonths(Integer adminId, Integer fromMonth, Integer toMonth)
			throws LogException, SalesException {

		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);
		
		if(cas.isPresent())
		{
			LocalDate now = LocalDate.now();
			int year = now.getYear();
			
			LocalDate from = null;
			
			if(String.valueOf(fromMonth).length() == 1 && toMonth <= fromMonth)
			{
				year -= 1;
				from = LocalDate.parse(year+"-0"+fromMonth+"-01");
			}
			else if(String.valueOf(fromMonth).length() != 1 && toMonth <= fromMonth)
			{
				year -= 1;
				from = LocalDate.parse(year+"-"+fromMonth+"-01");
			}
			else if(String.valueOf(fromMonth).length() == 1)
			{
				from = LocalDate.parse(year+"-0"+fromMonth+"-01");
			}
			else
			{
				from = LocalDate.parse(year+"-"+fromMonth+"-01");
			}
				
			LocalDate to = null;
			year = now.getYear();
			
			if(String.valueOf(toMonth).length() == 1)
			{
				to = LocalDate.parse(year+"-0"+toMonth+"-01");
				to = LocalDate.parse(year+"-0"+toMonth+"-"+to.getMonth().maxLength());
			}
			else
			{
				to = LocalDate.parse(year+"-"+toMonth+"-01");
				to = LocalDate.parse(year+"-"+toMonth+"-"+to.getMonth().maxLength());
			}
			
			List<Sales> salesList = sRepo.findByDeliveryDateBetween(from, to);
			
			if(salesList.size() != 0)
			{
				List<SalesDTO> resList = new ArrayList<>();
				
				for(Sales s:salesList)
				{
					User user = s.getUser();
					UserDTO userdto = new UserDTO(user.getUserId(), 
							user.getFirstName()+" "+user.getLastName(), user.getEmailId());
					
					Product p1 = s.getProduct();
					ProductDTO productdto = new ProductDTO(p1.getProductId(), p1.getProductTitle(), 
							p1.getImage(), p1.getPrice(), s.getQuantity(), p1.getProductRating(),  
							p1.getProductCategory(), p1.getBrand());
					
					resList.add(new SalesDTO(s.getSalesId(), userdto, productdto, s.getQuantity(), 
							s.getTotalPrice(), s.getDeliveryDate(), s.getPaymentType(), s.getOrderStatus()));
				}
				return resList;
			}
			throw new SalesException("No one sales made in between these two months..!");
		}
		else throw new LogException("Please Login as admin first...");
	}

}
