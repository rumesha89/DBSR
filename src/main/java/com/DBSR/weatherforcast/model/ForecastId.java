package com.DBSR.weatherforcast.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ForecastId {
	
	private String location;
	private LocalDate date;

}
