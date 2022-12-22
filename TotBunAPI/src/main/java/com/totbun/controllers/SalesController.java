package com.totbun.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.totbun.DTOs.SalesDTO;
import com.totbun.exceptions.LogException;
import com.totbun.exceptions.SalesException;
import com.totbun.services.SalesService;

@RestController
@RequestMapping("/totbun")
public class SalesController {
	
	@Autowired
	private SalesService sService;
	
	@GetMapping("/admin/sales-made-today/{adminId}")
	public ResponseEntity<List<SalesDTO>> salesMadeToday(@PathVariable("adminId") Integer adminId) throws LogException, SalesException
	{
		List<SalesDTO> sdto = sService.salesMadeToday(adminId);
		
		return new ResponseEntity<List<SalesDTO>>(sdto, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/admin/sales-made-last-week/{adminId}")
	public ResponseEntity<List<SalesDTO>> salesMadeLastWeek(@PathVariable("adminId") Integer adminId) throws LogException, SalesException
	{
		List<SalesDTO> sdto = sService.salesMadeLastWeek(adminId);
		
		return new ResponseEntity<List<SalesDTO>>(sdto, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/admin/sales-made-last-month/{adminId}")
	public ResponseEntity<List<SalesDTO>> salesMadeLastMonth(@PathVariable("adminId") Integer adminId) throws LogException, SalesException
	{
		List<SalesDTO> sdto = sService.salesMadeLastMonth(adminId);
		
		return new ResponseEntity<List<SalesDTO>>(sdto, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/admin/sales-between-months/{adminId}/{fromMonth}/{toMonth}")
	public ResponseEntity<List<SalesDTO>> salesBetweenMonths(@PathVariable("adminId") Integer adminId,
			@PathVariable("fromMonth") Integer fromMonth,
			@PathVariable("toMonth") Integer toMonth) throws LogException, SalesException
	{
		List<SalesDTO> sdto = sService.salesBetweenMonths(adminId, fromMonth, toMonth);
		
		return new ResponseEntity<List<SalesDTO>>(sdto, HttpStatus.FOUND);
		
	}

}
