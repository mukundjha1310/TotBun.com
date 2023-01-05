package com.totbun.services;

import java.util.List;
import com.totbun.DTOs.SalesDTO;
import com.totbun.exceptions.SalesException;

public interface SalesService {
	
	public List<SalesDTO> salesMadeToday() throws SalesException;
	
	public List<SalesDTO> salesMadeLastWeek() throws SalesException;
	
	public List<SalesDTO> salesMadeLastMonth() throws SalesException;
	
	public List<SalesDTO> salesBetweenMonths(Integer fromMonth, Integer toMonth) throws SalesException;
}
