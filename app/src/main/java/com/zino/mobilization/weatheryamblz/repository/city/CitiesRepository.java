package com.zino.mobilization.weatheryamblz.repository.city;

import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface CitiesRepository {
    Observable<CityEntity> getCity(String id);
    Observable<List<CityEntity>> getAllCities();
    Completable addCity(CityEntity city);
    Completable updateCity(CityEntity city);
    Completable removeCity(String cityId);
}
