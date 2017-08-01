package com.zino.mobilization.weatheryamblz.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zino.mobilization.weatheryamblz.data.db.entity.City;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityWithForecast;

import io.reactivex.Flowable;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

@Dao
public interface CityDao {

    @Query("SELECT * FROM cities")
    Flowable<City> getAllCities();

    @Query("SELECT * FROM cities")
    Flowable<CityWithForecast> getAllCitiesWithForecast();

    @Query("SELECT * FROM cities WHERE id = :id")
    Flowable<CityWithForecast> getCityWithForecastById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCity(City city);

}
