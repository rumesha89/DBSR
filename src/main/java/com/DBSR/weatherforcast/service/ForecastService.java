package com.DBSR.weatherforcast.service;

import java.util.List;

import com.DBSR.weatherforcast.model.Forecast;

public interface ForecastService {

	public List<Forecast> getForecastforToday();
	
	public void save(Forecast forecast);

	public void housekeepData();
	
}
