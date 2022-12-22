package com.totbun.services;

import java.util.List;
import com.totbun.DTOs.SalesDTO;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.SalesException;

public interface SalesService {
	
	public List<SalesDTO> salesMadeToday(Integer adminId)throws LogException, SalesException;
	
	public List<SalesDTO> salesMadeLastWeek(Integer adminId)throws LogException, SalesException;
	
	public List<SalesDTO> salesMadeLastMonth(Integer adminId)throws LogException, SalesException;
	
	public List<SalesDTO> salesBetweenMonths(Integer adminId, Integer fromMonth, Integer toMonth)throws LogException, SalesException;
}
