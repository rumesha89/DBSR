package com.DBSR.weatherforcast.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DBSR.weatherforcast.model.Forecast;
import com.DBSR.weatherforcast.service.ForecastService;

@RestController
public class ForecastController {
	
	@Autowired
	ForecastService forecastService;
	
	@GetMapping("/getAll")
	public List<Forecast> listDepts() throws ParseException{
		
		return forecastService.getForecastforToday();
	}
	
	@PostMapping("/add")
	public Forecast createDept(@RequestBody Forecast forecast) {
		forecastService.save(forecast);
		return forecast;
	}
}
