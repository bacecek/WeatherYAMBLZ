package com.zino.mobilization.weatheryamblz.di.module;

import com.zino.mobilization.weatheryamblz.business.interactor.cities.CitiesInteractor;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.presentation.cities.CitiesPresenter;
import com.zino.mobilization.weatheryamblz.presentation.settings.SettingsPresenter;
import com.zino.mobilization.weatheryamblz.presentation.weather.WeatherPresenter;
import com.zino.mobilization.weatheryamblz.repository.weather.WeatherRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis Buzmakov on 29.07.17.
 * <buzmakov.da@gmail.com>
 */

@Module
public class PresenterModule {

    @Provides
    SettingsPresenter provideSettingsPresenter(SettingsManager preferencesHelper) {
        return new SettingsPresenter(preferencesHelper);
    }

    @Provides
    WeatherPresenter provideWeatherPresenter(SettingsManager preferencesHelper,
                                              WeatherRepository repository) {
        return new WeatherPresenter(preferencesHelper, repository);
    }

    @Provides
    CitiesPresenter provideCitiesPresenter(CitiesInteractor interactor) {
        return new CitiesPresenter(interactor);
    }

}
