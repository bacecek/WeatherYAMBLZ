package com.zino.mobilization.weatheryamblz.di.component;

import com.zino.mobilization.weatheryamblz.di.module.AppModule;
import com.zino.mobilization.weatheryamblz.di.module.NetworkModule;
import com.zino.mobilization.weatheryamblz.di.module.PresenterModule;
import com.zino.mobilization.weatheryamblz.data.cache.prefs.SharedPreferencesHelper;
import com.zino.mobilization.weatheryamblz.presentation.settings.SettingsPresenter;
import com.zino.mobilization.weatheryamblz.presentation.weather.WeatherPresenter;
import com.zino.mobilization.weatheryamblz.data.service.UpdateWeatherService;
import com.zino.mobilization.weatheryamblz.utils.AndroidJobHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, PresenterModule.class})
public interface AppComponent {
    void inject(UpdateWeatherService service);

    SharedPreferencesHelper getPreferenceHelper();
    AndroidJobHelper getJobHelper();

    SettingsPresenter getSettingsPresenter();
    WeatherPresenter getWeatherPresenter();
}
