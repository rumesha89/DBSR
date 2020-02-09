package com.DBSR.weatherforcast.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document("Forecast")
public class Forecast {

	@Id
	private ForecastId id;
	
	private String timezone;
	private String summary;
	private String icon;
	private Double humidity;
	private Double temperatureMin;
	private Double temperatureMax;
	
	public Forecast(ForecastId id, String timezone, String summary, String icon, Double humidity, Double temperatureMin,
			Double temperatureMax) {
		super();
		this.id = id;
		this.timezone = timezone;
		this.summary = summary;
		this.icon = icon;
		this.humidity = humidity;
		this.temperatureMin = temperatureMin;
		this.temperatureMax = temperatureMax;
	}
	
	
	
}
