package com.zino.mobilization.weatheryamblz.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zino.mobilization.weatheryamblz.data.db.entity.ForecastEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

@Dao
public interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ForecastEntity> forecasts);

    @Query("SELECT * FROM forecasts WHERE cityId = :cityId")
    Flowable<List<ForecastEntity>> getForecastsByCityId(long cityId);
}
