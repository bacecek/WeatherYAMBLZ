package com.zino.mobilization.weatheryamblz.business.interactor.service;

import io.reactivex.Completable;

/**
 * Created by Denis Buzmakov on 06.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface ServiceInteractor {
    Completable fetchAndSaveWeather(String cityId);
    Completable fetchAndSaveAllCities();
}
