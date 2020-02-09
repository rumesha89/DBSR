package com.DBSR.weatherforcast.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document("Forecast")
public class Forecast {

	@Id
	private ForecastId id;
	
	private String summary;
	private String icon;
	
	public Forecast(ForecastId id, String summary, String icon) {
		super();
		this.id = id;
		this.summary = summary;
		this.icon = icon;
	}
	
	
}
