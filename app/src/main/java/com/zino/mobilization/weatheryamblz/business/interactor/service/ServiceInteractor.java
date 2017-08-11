package com.zino.mobilization.weatheryamblz.business.interactor.service;

import com.zino.mobilization.weatheryamblz.business.interactor.fetch_weather.FetchWeatherInteractor;

import io.reactivex.Completable;

/**
 * Created by Denis Buzmakov on 06.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface ServiceInteractor extends FetchWeatherInteractor {
    Completable fetchAndSaveAllCities();
}
