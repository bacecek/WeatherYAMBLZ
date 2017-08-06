package com.zino.mobilization.weatheryamblz.repository.city;

import com.zino.mobilization.weatheryamblz.data.db.dao.CityDao;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CitiesRepositoryImpl implements CitiesRepository {
    private CityDao cityDao;

    @Inject
    public CitiesRepositoryImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public Single<CityEntity> getCity(String id) {
        Timber.d("get city by id: " + id);
        return cityDao.getCityById(id);
    }

    @Override
    public Observable<List<CityEntity>> getAllCities() {
        Timber.d("get al cities");
        return cityDao.getAllCities()
                .toObservable();
    }

    @Override
    public Completable addCity(CityEntity city) {
        Timber.d("save city: " + city.toString());
        return Completable.fromAction(() -> cityDao.insertCity(city));
    }

    @Override
    public Completable updateCity(CityEntity city) {
        Timber.d("update city: " + city.toString());
        return Completable.fromAction(() -> cityDao.updateCity(city));
    }

    @Override
    public Completable removeCity(String cityId) {
        Timber.d("remove city " + cityId);
        return Completable.fromAction(() -> cityDao.removeCity(cityId));
    }
}
