package com.zino.mobilization.weatheryamblz.repository.weather;

import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;

import io.reactivex.Single;

/**
 * Created by Алексей on 15.07.2017.
 */

public interface WeatherRepository {
    Single<WeatherResponse> getCurrentWeatherFromApi(double lat, double lon);
    //Observable<List<WeatherEntity>> getAllCurrentWeathers();
    //Observable<WeatherEntity> getCurrentWeatherFromDb(String cityId);
}
