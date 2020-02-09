package com.DBSR.weatherforcast.model.externalresponse;

import lombok.Data;


@Data
public class ExternalForecastResponse {
	
	String timezone;
	Daily daily;
	
	
	
}


