package com.DBSR.weatherforcast.serviceImpl;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DBSR.weatherforcast.model.Forecast;
import com.DBSR.weatherforcast.repository.ForecastRepository;
import com.DBSR.weatherforcast.service.ForecastService;

@Service
public class ForecastServiceImpl implements ForecastService{
	
	@Autowired
	ForecastRepository forecastRepository;

	@Override
	public List<Forecast> getForecastforToday() throws ParseException {
		
		return forecastRepository.findByForecastDate(LocalDate.now());
	}

	@Override
	public void save(Forecast forecast) {
		forecastRepository.save(forecast);
	}

}
