package com.DBSR.weatherforcast.model.externalresponse;

import java.util.List;

import lombok.Data;

@Data
public class Daily {
	
	String summary;
	String icon;
	
	List<DailyData> data;
	
	
	
}