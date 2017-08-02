package com.zino.mobilization.weatheryamblz.repository;

import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Алексей on 15.07.2017.
 */

public interface WeatherRepository {

    Observable<WeatherResponse> getCurrentWeather(double lat, double lon);

    Single<WeatherResponse> getCurrentWeatherFromApi(double lat, double lon);

    Single<WeatherResponse> getCurrentWeatherFromCache();

    void saveCurrentWeather(WeatherResponse response);

}