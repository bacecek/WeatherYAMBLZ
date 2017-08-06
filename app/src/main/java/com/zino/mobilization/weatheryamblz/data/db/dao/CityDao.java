package com.zino.mobilization.weatheryamblz.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

@Dao
public interface CityDao {

    @Query("SELECT * FROM cities")
    Flowable<List<CityEntity>> getAllCities();

    @Query("SELECT * FROM cities WHERE id = :id")
    Single<CityEntity> getCityById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCity(CityEntity city);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCity(CityEntity city);

    @Query("DELETE FROM cities WHERE id = :cityId")
    void removeCity(String cityId);
}
