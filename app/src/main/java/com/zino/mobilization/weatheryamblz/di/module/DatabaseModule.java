package com.zino.mobilization.weatheryamblz.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.zino.mobilization.weatheryamblz.data.db.AppDatabase;
import com.zino.mobilization.weatheryamblz.data.db.dao.CityDao;
import com.zino.mobilization.weatheryamblz.data.db.dao.ForecastDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis Buzmakov on 03.08.17.
 * <buzmakov.da@gmail.com>
 */

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .build();
    }

    @Provides
    @Singleton
    CityDao provideCityDao(AppDatabase database) {
        return database.cityDao();
    }

    /*@Provides
    @Singleton
    WeatherDao provideWeatherDao(AppDatabase database) {
        return database.weatherDao();
    }*/

    @Provides
    @Singleton
    ForecastDao provideForecastDao(AppDatabase database) {
        return database.forecastDao();
    }

}
