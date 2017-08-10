package com.zino.mobilization.weatheryamblz.di.module;

import com.zino.mobilization.weatheryamblz.business.interactor.add_city.AddCityInteractor;
import com.zino.mobilization.weatheryamblz.business.interactor.cities.CitiesInteractor;
import com.zino.mobilization.weatheryamblz.business.interactor.weather.WeatherInteractor;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.presentation.add_city.AddCityPresenter;
import com.zino.mobilization.weatheryamblz.presentation.cities.CitiesPresenter;
import com.zino.mobilization.weatheryamblz.presentation.settings.SettingsPresenter;
import com.zino.mobilization.weatheryamblz.presentation.weather.WeatherPresenter;
import com.zino.mobilization.weatheryamblz.utils.AppResources;

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
    WeatherPresenter provideWeatherPresenter(WeatherInteractor interactor,
                                             AppResources resources) {
        return new WeatherPresenter(interactor, resources);
    }

    @Provides
    CitiesPresenter provideCitiesPresenter(CitiesInteractor interactor,
                                           AppResources resources) {
        return new CitiesPresenter(interactor, resources);
    }

    @Provides
    AddCityPresenter provideAddCityPresenter(AddCityInteractor interactor,
                                             AppResources resources) {
        return new AddCityPresenter(interactor, resources);
    }

}
