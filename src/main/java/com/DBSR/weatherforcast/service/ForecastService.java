package com.DBSR.weatherforcast.service;

import java.text.ParseException;
import java.util.List;

import com.DBSR.weatherforcast.model.Forecast;

public interface ForecastService {

	public List<Forecast> getForecastforToday() throws ParseException;
	
	public void save(Forecast forecast);
	
}
