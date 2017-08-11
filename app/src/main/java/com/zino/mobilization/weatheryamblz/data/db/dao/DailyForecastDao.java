package com.zino.mobilization.weatheryamblz.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zino.mobilization.weatheryamblz.data.db.entity.DailyForecastEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Denis Buzmakov on 06.08.17.
 * <buzmakov.da@gmail.com>
 */

@Dao
public interface DailyForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<DailyForecastEntity> forecasts);

    @Query("SELECT * FROM daily_forecasts WHERE cityId = :cityId")
    Flowable<List<DailyForecastEntity>> getForecastsByCityId(String cityId);

    @Query("DELETE FROM daily_forecasts WHERE cityId = :cityId")
    void removeForecastsByCityId(String cityId);
}
