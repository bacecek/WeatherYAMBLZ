package com.zino.mobilization.weatheryamblz.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

@Dao
public interface CityDao {

    @Query("SELECT * FROM cities ORDER BY timestamp")
    Flowable<List<CityEntity>> getAllCities();

    //"Since Reactive Streams does not allow null, if the query returns a nullable type
    // it will not dispatch anything if the value is null."
    @Query("SELECT * FROM cities WHERE id = :id")
    Flowable<CityEntity[]> getCityById(String id);

    @Insert
    void insertCity(CityEntity city);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCity(CityEntity city);

    @Query("DELETE FROM cities WHERE id = :cityId")
    void removeCity(String cityId);
}
