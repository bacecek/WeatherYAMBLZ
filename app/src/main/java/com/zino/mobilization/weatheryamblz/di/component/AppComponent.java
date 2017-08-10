package com.zino.mobilization.weatheryamblz.di.component;

import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.data.service.UpdateWeatherService;
import com.zino.mobilization.weatheryamblz.di.module.AppModule;
import com.zino.mobilization.weatheryamblz.di.module.BindsModule;
import com.zino.mobilization.weatheryamblz.di.module.DatabaseModule;
import com.zino.mobilization.weatheryamblz.di.module.NetworkModule;
import com.zino.mobilization.weatheryamblz.di.module.PresenterModule;
import com.zino.mobilization.weatheryamblz.di.module.StringsModule;
import com.zino.mobilization.weatheryamblz.presentation.add_city.AddCityPresenter;
import com.zino.mobilization.weatheryamblz.presentation.cities.CitiesPresenter;
import com.zino.mobilization.weatheryamblz.presentation.settings.SettingsPresenter;
import com.zino.mobilization.weatheryamblz.presentation.weather.WeatherPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

@Singleton
@Component(modules = {AppModule.class,
        NetworkModule.class,
        PresenterModule.class,
        BindsModule.class,
        DatabaseModule.class,
        StringsModule.class})
public interface AppComponent {
    void inject(UpdateWeatherService service);
    void inject(WeatherApplication application);

    SettingsPresenter getSettingsPresenter();
    WeatherPresenter getWeatherPresenter();
    CitiesPresenter getCitiesPresenter();
    AddCityPresenter getAddCityPresenter();
}
