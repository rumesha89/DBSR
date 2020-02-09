package com.DBSR.weatherforcast.model.externalresponse;

import lombok.Data;

@Data
public class DailyData{
	String summary;
	String icon;
	Double humidity;
	Double temperatureMin;
	Double temperatureMax;

}