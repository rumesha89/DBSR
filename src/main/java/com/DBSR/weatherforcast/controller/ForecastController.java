package com.DBSR.weatherforcast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.DBSR.weatherforcast.model.Forecast;
import com.DBSR.weatherforcast.service.ForecastService;

@Controller
public class ForecastController {
	
	@Autowired
	ForecastService forecastService;
	
	@GetMapping("/")
	public ModelAndView getAll(){
		
		List<Forecast> forecastList = forecastService.getForecastforToday();
//		return forecastService.getForecastforToday();
		return new ModelAndView("index", "forecastList", forecastService.getForecastforToday());
	}
	
	@PostMapping("/add")
	public Forecast createDept(@RequestBody Forecast forecast) {
		forecastService.save(forecast);
		return forecast;
	}
}
