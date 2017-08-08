package com.zino.mobilization.weatheryamblz.presentation.weather;


import android.arch.persistence.room.EmptyResultSetException;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.business.interactor.weather.WeatherInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {
    private WeatherInteractor interactor;
    private String cityId;

    public WeatherPresenter(WeatherInteractor interactor) {
        this.interactor = interactor;
    }

    public void setCityId(String cityId) {
        if(this.cityId == null) {
            this.cityId = cityId;

            interactor.getCity(cityId)
                    .doOnNext(city -> {
                        if(city.getCurrentWeather() == null) {
                            onRefresh();
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(city -> getViewState().showCity(city),
                            error -> {
                                getViewState().hideLoading();
                                if(error instanceof EmptyResultSetException) {
                                    getViewState().removeYourself();
                                }
                            });

            interactor.getHourlyForecast(cityId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(forecasts -> getViewState().showHourlyForecasts(forecasts),
                            error -> getViewState().hideLoading());

            interactor.getDailyForecast(cityId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(forecasts -> getViewState().showDailyForecasts(forecasts),
                            error -> getViewState().hideLoading());
        }
    }

    public void onRefresh() {
        if(cityId != null) {
            interactor.fetchAndSaveWeather(cityId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> getViewState().hideLoading(),
                            error -> getViewState().hideLoading());

            interactor.fetchAndSaveHourlyForecasts(cityId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> getViewState().hideLoading(),
                            error -> getViewState().hideLoading());

            interactor.fetchAndSaveDailyForecasts(cityId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> getViewState().hideLoading(),
                            error -> getViewState().hideLoading());
        }
    }
}
