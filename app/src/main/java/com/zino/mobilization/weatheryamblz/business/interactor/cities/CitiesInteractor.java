package com.zino.mobilization.weatheryamblz.business.interactor.cities;

import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.interactor.base.BaseInteractor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Denis Buzmakov on 03.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface CitiesInteractor extends BaseInteractor {
    Observable<List<City>> getCities();
    Observable<List<City>> getCitiesWithoutWeather();
    Completable removeCity(String cityId);
}
