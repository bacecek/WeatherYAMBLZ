package com.zino.mobilization.weatheryamblz.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zino.mobilization.weatheryamblz.data.db.entity.HourlyForecastEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

@Dao
public interface HourlyForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<HourlyForecastEntity> forecasts);

    @Query("SELECT * FROM hourly_forecasts WHERE cityId = :cityId")
    Flowable<List<HourlyForecastEntity>> getForecastsByCityId(String cityId);

    @Query("DELETE FROM hourly_forecasts WHERE cityId = :cityId")
    void removeForecastsByCityId(String cityId);
}
