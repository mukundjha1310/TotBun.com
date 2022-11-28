package com.totbun.services;

import java.util.List;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.SalesException;
import com.totbun.modules.Sales;

public interface SalesServices {
	
	public List<Sales> seeSalesMadeToday(Integer adminId) throws LogException, SalesException;
	public List<Sales> seeSalesMadeLastWeek(Integer adminId) throws LogException, SalesException;
	public List<Sales> seeSalesMadeLastMonth(Integer adminId) throws LogException, SalesException;
	public List<Sales> seeSalesFromJanToDec(Integer adminId) throws LogException, SalesException;
}
