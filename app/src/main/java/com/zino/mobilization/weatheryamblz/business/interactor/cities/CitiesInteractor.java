package com.zino.mobilization.weatheryamblz.business.interactor.cities;

import com.zino.mobilization.weatheryamblz.business.entity.City;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Denis Buzmakov on 03.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface CitiesInteractor {
    Observable<List<City>> getCities();
    Observable<List<City>> getCitiesWithoutWeather();
    Completable addCity(City city);
    Completable fetchAndSaveWeather(String cityId);
    Completable removeCity(String cityId);
}
