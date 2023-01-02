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
import com.totbun.exceptions.SalesException;
import com.totbun.services.SalesService;

@RestController
@RequestMapping("/totbun")
public class SalesController {
	
	@Autowired
	private SalesService sService;
	
	@GetMapping("/admin/sales-made-today")
	public ResponseEntity<List<SalesDTO>> salesMadeToday() throws SalesException
	{
		List<SalesDTO> sdto = sService.salesMadeToday();
		
		return new ResponseEntity<List<SalesDTO>>(sdto, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/admin/sales-made-last-week")
	public ResponseEntity<List<SalesDTO>> salesMadeLastWeek() throws SalesException
	{
		List<SalesDTO> sdto = sService.salesMadeLastWeek();
		
		return new ResponseEntity<List<SalesDTO>>(sdto, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/admin/sales-made-last-month")
	public ResponseEntity<List<SalesDTO>> salesMadeLastMonth() throws SalesException
	{
		List<SalesDTO> sdto = sService.salesMadeLastMonth();
		
		return new ResponseEntity<List<SalesDTO>>(sdto, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/admin/sales-between-months/{fromMonth}/{toMonth}")
	public ResponseEntity<List<SalesDTO>> salesBetweenMonths(@PathVariable("fromMonth") Integer fromMonth,
			@PathVariable("toMonth") Integer toMonth) throws SalesException
	{
		List<SalesDTO> sdto = sService.salesBetweenMonths(fromMonth, toMonth);
		
		return new ResponseEntity<List<SalesDTO>>(sdto, HttpStatus.FOUND);
		
	}

}
