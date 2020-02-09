package com.DBSR.weatherforcast.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.DBSR.weatherforcast.model.Forecast;
import com.DBSR.weatherforcast.model.ForecastId;

public interface ForecastRepository extends MongoRepository<Forecast, ForecastId>{

	@Query(value = "{'id.date': ?0}")
    List<Forecast> findByForecastDate(LocalDate date);
	
}
