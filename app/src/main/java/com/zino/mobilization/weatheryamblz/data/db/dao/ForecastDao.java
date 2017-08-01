package com.zino.mobilization.weatheryamblz.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.zino.mobilization.weatheryamblz.data.db.entity.Forecast;

import java.util.List;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

@Dao
public interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Forecast> forecasts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Forecast forecast);
}
