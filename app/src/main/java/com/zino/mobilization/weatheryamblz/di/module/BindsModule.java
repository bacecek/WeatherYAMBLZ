package com.zino.mobilization.weatheryamblz.di.module;

import com.zino.mobilization.weatheryamblz.business.interactor.cities.CitiesInteractor;
import com.zino.mobilization.weatheryamblz.business.interactor.cities.CitiesInteractorImpl;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepository;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepositoryImpl;
import com.zino.mobilization.weatheryamblz.repository.forecast.ForecastRepository;
import com.zino.mobilization.weatheryamblz.repository.forecast.ForecastRepositoryImpl;
import com.zino.mobilization.weatheryamblz.repository.weather.WeatherRepository;
import com.zino.mobilization.weatheryamblz.repository.weather.WeatherRepositoryImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

@Module
public abstract class BindsModule {

    @Binds
    public abstract CitiesRepository bindsCitiesRepository(CitiesRepositoryImpl repository);

    @Binds
    public abstract WeatherRepository bindsWeatherRepository(WeatherRepositoryImpl repository);

    @Binds
    public abstract ForecastRepository bindsForecastRepository(ForecastRepositoryImpl repository);

    @Binds
    abstract CitiesInteractor bindsCitiesInteractor(CitiesInteractorImpl interactor);
}
