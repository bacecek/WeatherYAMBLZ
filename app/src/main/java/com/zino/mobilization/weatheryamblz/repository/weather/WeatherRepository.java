package com.zino.mobilization.weatheryamblz.repository.weather;

import com.zino.mobilization.weatheryamblz.data.db.entity.DailyForecastEntity;
import com.zino.mobilization.weatheryamblz.data.db.entity.HourlyForecastEntity;
import com.zino.mobilization.weatheryamblz.data.network.response.forecast.daily.DailyForecastResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.forecast.hourly.HourlyForecastResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Denis Buzmakov on 15.07.2017.
 * <buzmakov.da@gmail.com>
 */

public interface WeatherRepository {
    Single<WeatherResponse> getCurrentWeatherFromApi(double lat, double lon);
    Single<HourlyForecastResponse> getHourlyForecastFromApi(double lat, double lon);
    Single<DailyForecastResponse> getDailyForecastFromApi(double lat, double lon);
    Observable<List<HourlyForecastEntity>> getHourlyForecasts(String cityId);
    Completable saveHourlyForecast(List<HourlyForecastEntity> forecasts);
    Observable<List<DailyForecastEntity>> getDailyForecasts(String cityId);
    Completable saveDailyForecast(List<DailyForecastEntity> forecasts);
}
