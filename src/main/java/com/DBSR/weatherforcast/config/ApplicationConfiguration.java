package com.DBSR.weatherforcast.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationConfiguration {

	@Autowired
	private Environment env;
	
	public String getAPIKey() {
		return env.getProperty("ApiKey");
	}
}