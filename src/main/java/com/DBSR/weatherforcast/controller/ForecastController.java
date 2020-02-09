package com.DBSR.weatherforcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.DBSR.weatherforcast.service.ForecastService;

@Controller
public class ForecastController {
	
	@Autowired
	ForecastService forecastService;
	
	@GetMapping("/")
	public ModelAndView getAll(){
		return new ModelAndView("index", "forecastList", forecastService.getForecastforToday());
	}
}
