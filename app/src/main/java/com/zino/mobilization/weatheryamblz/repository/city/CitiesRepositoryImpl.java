package com.zino.mobilization.weatheryamblz.repository.city;

import android.arch.persistence.room.EmptyResultSetException;

import com.zino.mobilization.weatheryamblz.data.db.dao.CityDao;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
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
    public Observable<CityEntity> getCity(String id) {
        Timber.d("get city by id: %s", id);
        return cityDao.getCityById(id)
                .doOnNext(list -> {
                    if(list.length == 0) {
                        throw new EmptyResultSetException("City is no longer exists");
                    }
                })
                .map(list -> list[0])
                .toObservable();
    }

    @Override
    public Observable<List<CityEntity>> getAllCities() {
        Timber.d("get al cities");
        return cityDao.getAllCities()
                .toObservable();
    }

    @Override
    public Completable addCity(CityEntity city) {
        Timber.d("save city: %s", city.toString());
        return Completable.fromAction(() -> cityDao.insertCity(city));
    }

    @Override
    public Completable updateCity(CityEntity city) {
        Timber.d("update city: %s", city.toString());
        return Completable.fromAction(() -> cityDao.updateCity(city));
    }

    @Override
    public Completable removeCity(String cityId) {
        Timber.d("remove city %s", cityId);
        return Completable.fromAction(() -> cityDao.removeCity(cityId));
    }
}
