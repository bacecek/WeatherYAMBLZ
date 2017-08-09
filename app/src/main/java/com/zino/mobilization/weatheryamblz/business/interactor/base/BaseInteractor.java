package com.zino.mobilization.weatheryamblz.business.interactor.base;

import io.reactivex.Completable;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface BaseInteractor {
    Completable fetchAndSaveWeather(String cityId);
    Completable fetchAndSaveHourlyForecasts(String cityId);
    Completable fetchAndSaveDailyForecasts(String cityId);
}
