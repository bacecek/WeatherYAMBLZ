package com.zino.mobilization.weatheryamblz.business.interactor.weather;

import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.entity.DailyForecast;
import com.zino.mobilization.weatheryamblz.business.entity.HourlyForecast;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Denis Buzmakov on 04.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface WeatherInteractor {
    Observable<City> getCity(String cityId);
    Observable<List<HourlyForecast>> getHourlyForecast(String cityId);
    Observable<List<DailyForecast>> getDailyForecast(String cityId);
    Completable fetchAndSaveWeather(String cityId);
    Completable fetchAndSaveHourlyForecasts(String cityId);
    Completable fetchAndSaveDailyForecasts(String cityId);
}
