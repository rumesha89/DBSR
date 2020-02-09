package com.DBSR.weatherforcast.component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.DBSR.weatherforcast.service.ForecastService;

@Component
public class HousekeepData {
	
	    private static final Logger logger = LoggerFactory.getLogger(HousekeepData.class);
	    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	    
	    @Autowired
		ForecastService forecastService;

	    @Scheduled(fixedRate = 1 * 24 * 3600 * 1000)
	    public void housekeepData() {
	        logger.info("Housekeeping Data :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
	        forecastService.housekeepData();
	    }
	}
