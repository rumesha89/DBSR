package com.DBSR.weatherforcast.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.DBSR.weatherforcast.enums.LocationEnum;
import com.DBSR.weatherforcast.model.Forecast;
import com.DBSR.weatherforcast.model.ForecastId;
import com.DBSR.weatherforcast.model.externalresponse.ExternalForecastResponse;
import com.DBSR.weatherforcast.repository.ForecastRepository;
import com.DBSR.weatherforcast.service.ForecastService;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Service
@Transactional
public class ForecastServiceImpl implements ForecastService {

	@Autowired
	ForecastRepository forecastRepository;

	/**
	 * Fetch and return weather forecast for TODAY
	 */
	@Override
	public List<Forecast> getForecastforToday() {

		List<Observable<Forecast>> responseList = new ArrayList<Observable<Forecast>>();
		Observable<List<Forecast>> fetchedData;

		// get Data from Database for the Day
		List<Forecast> forecastData = forecastRepository.findByForecastDate(LocalDate.now());

		// check weather all the data present
		Arrays.asList(LocationEnum.values()).forEach(l -> {
			if (!checkDataIsPresent(forecastData, l.name())) {
				responseList.add(getForecastApiData(l));
			}
		});

		// call back external API to fetch missing data
		if (!responseList.isEmpty()) {
			fetchedData = Observable.zip(responseList, objects -> {
				for (Object obj : objects) {
					//persist data to the DB
					save((Forecast) obj);
					forecastData.add((Forecast) obj);
				}
				return forecastData;
			}).onErrorReturn(throwable -> {
				return forecastData;
			});
		} else {
			fetchedData = Observable.just(forecastData);
		}

		return fetchedData.toBlocking().first();
	}

	/**
	 * Check if forecast data is present in the given collection
	 * @param list
	 * @param location
	 * @return
	 */
	private boolean checkDataIsPresent(List<Forecast> list, String location) {
		return list.stream().filter(o -> o.getId().getLocation().equals(location)).findFirst().isPresent();
	}	

	/**
	 * get observable Forecast Data and map
	 * @param l
	 * @return
	 */
	private Observable<Forecast> getForecastApiData(LocationEnum l) {
		return Observable.fromCallable(() -> fetchWeatherData(l))
				.map(new Func1<ExternalForecastResponse, Forecast> () {
					@Override
					public Forecast call(ExternalForecastResponse t) {
						Forecast temp = new Forecast(
								new ForecastId(l.name(), LocalDate.now()),
								l.getLocationCode(),
								t.getDaily().getData().get(0).getSummary(),
								t.getDaily().getData().get(0).getIcon(),
								t.getDaily().getData().get(0).getHumidity(),
								t.getDaily().getData().get(0).getTemperatureMin(),
								t.getDaily().getData().get(0).getTemperatureMax()
								);
						return temp;
					}
				})
				.subscribeOn(Schedulers.computation());
	}
     
	/**
	 * External API call
	 * @param location
	 * @return [ExternalForecastResponse]
	 */
	private ExternalForecastResponse fetchWeatherData(LocationEnum location) {
		final String url = "https://api.darksky.net/forecast/a95dd3618394987a073d661b67dc1adc/" + location.getLat()
				+ "," + location.getLan() + "/?exclude=currently,flags,minutely,hourly";

		RestTemplate template = new RestTemplate();
		ExternalForecastResponse result = template.getForObject(url, ExternalForecastResponse.class);

		return result;
	}

	/**
	 * Save Forecast
	 */
	@Override
	public void save(Forecast forecast) {
		forecastRepository.save(forecast);
	}

}
