package com.zino.mobilization.weatheryamblz.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.zino.mobilization.weatheryamblz.data.db.dao.CityDao;
import com.zino.mobilization.weatheryamblz.data.db.dao.ForecastDao;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;
import com.zino.mobilization.weatheryamblz.data.db.entity.ForecastEntity;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

@Database(entities = {CityEntity.class, ForecastEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public static String DATABASE_NAME = "app_database";

    public abstract CityDao cityDao();
    public abstract ForecastDao forecastDao();
}
