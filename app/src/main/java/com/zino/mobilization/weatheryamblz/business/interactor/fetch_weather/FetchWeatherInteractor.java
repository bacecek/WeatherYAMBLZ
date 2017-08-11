package com.zino.mobilization.weatheryamblz.business.interactor.fetch_weather;

import io.reactivex.Completable;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface FetchWeatherInteractor {
    Completable fetchAndSaveWeather(String cityId);
    Completable fetchAndSaveHourlyForecasts(String cityId);
    Completable fetchAndSaveDailyForecasts(String cityId);
    Completable fetchAndSaveAllCities();
}
