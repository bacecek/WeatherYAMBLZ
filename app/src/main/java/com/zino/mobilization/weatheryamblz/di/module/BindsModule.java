package com.zino.mobilization.weatheryamblz.di.module;

import com.zino.mobilization.weatheryamblz.business.interactor.add_city.AddCityInteractor;
import com.zino.mobilization.weatheryamblz.business.interactor.add_city.AddCityInteractorImpl;
import com.zino.mobilization.weatheryamblz.business.interactor.cities.CitiesInteractor;
import com.zino.mobilization.weatheryamblz.business.interactor.cities.CitiesInteractorImpl;
import com.zino.mobilization.weatheryamblz.business.interactor.service.ServiceInteractor;
import com.zino.mobilization.weatheryamblz.business.interactor.service.ServiceInteractorImpl;
import com.zino.mobilization.weatheryamblz.business.interactor.weather.WeatherInteractor;
import com.zino.mobilization.weatheryamblz.business.interactor.weather.WeatherInteractorImpl;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManagerImpl;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepository;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepositoryImpl;
import com.zino.mobilization.weatheryamblz.repository.places.PlacesRepository;
import com.zino.mobilization.weatheryamblz.repository.places.PlacesRepositoryImpl;
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

    @Binds abstract CitiesRepository bindsCitiesRepository(CitiesRepositoryImpl repository);

    @Binds abstract WeatherRepository bindsWeatherRepository(WeatherRepositoryImpl repository);

    @Binds abstract PlacesRepository bindsPlacesRepository(PlacesRepositoryImpl interactor);

    @Binds abstract WeatherInteractor bindsForecastRepository(WeatherInteractorImpl interactor);

    @Binds abstract CitiesInteractor bindsCitiesInteractor(CitiesInteractorImpl interactor);

    @Binds abstract ServiceInteractor bindsServiceInteractor(ServiceInteractorImpl interactor);

    @Binds abstract AddCityInteractor bindsAddCityInteractor(AddCityInteractorImpl interactor);

    @Binds abstract SettingsManager bindsSettingsManager(SettingsManagerImpl settingsManager);
}
