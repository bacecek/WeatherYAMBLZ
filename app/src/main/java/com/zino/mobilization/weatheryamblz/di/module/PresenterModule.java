package com.zino.mobilization.weatheryamblz.di.module;

import com.zino.mobilization.weatheryamblz.data.cache.prefs.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.repository.WeatherRepository;
import com.zino.mobilization.weatheryamblz.presentation.settings.SettingsPresenter;
import com.zino.mobilization.weatheryamblz.presentation.weather.WeatherPresenter;
import com.zino.mobilization.weatheryamblz.utils.AndroidJobHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis Buzmakov on 29.07.17.
 * <buzmakov.da@gmail.com>
 */

@Module
public class PresenterModule {

    @Provides
    SettingsPresenter provideSettingsPresenter(SharedPreferencesHelper preferencesHelper,
                                               AndroidJobHelper jobHelper) {
        return new SettingsPresenter(preferencesHelper, jobHelper);
    }

    @Provides
    WeatherPresenter provideWeatherPresenter(SharedPreferencesHelper preferencesHelper,
                                              WeatherRepository repository) {
        return new WeatherPresenter(preferencesHelper, repository);
    }

}
