package com.zino.mobilization.weatheryamblz.data.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.business.interactor.fetch_weather.FetchWeatherInteractor;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class UpdateWeatherService extends Service {

    private Disposable disposable;

    @Inject
    SettingsManager settingsManager;

    @Inject
    FetchWeatherInteractor fetchWeatherInteractor;

    public UpdateWeatherService(){
        WeatherApplication.getAppComponent().inject(this);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        loadWeather();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    void loadWeather() {
        disposable = fetchWeatherInteractor.fetchAndSaveAllCities()
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
