package com.zino.mobilization.weatheryamblz.presentation.weather;


import android.arch.persistence.room.EmptyResultSetException;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.business.interactor.weather.WeatherInteractor;
import com.zino.mobilization.weatheryamblz.utils.AppResources;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {
    private WeatherInteractor interactor;
    private AppResources resources;
    private String cityId;

    public WeatherPresenter(WeatherInteractor interactor, AppResources resources) {
        this.interactor = interactor;
        this.resources = resources;
    }

    public void setCityId(String cityId) {
        if(this.cityId == null) {
            this.cityId = cityId;

            interactor.getCity(cityId)
                    .doOnNext(city -> {
                        if(city.getCurrentWeather() == null) {
                            fetchInfo();
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> getViewState().setLoadingVisibility(false))
                    .subscribe(city -> getViewState().showCity(city),
                            error -> {
                                if(error instanceof EmptyResultSetException) {
                                    getViewState().removeYourself();
                                }
                            });

            interactor.getHourlyForecast(cityId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> getViewState().setLoadingVisibility(false))
                    .subscribe(forecasts -> getViewState().showHourlyForecasts(forecasts),
                            error -> showErrorMessage());

            interactor.getDailyForecast(cityId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> getViewState().setLoadingVisibility(false))
                    .subscribe(forecasts -> getViewState().showDailyForecasts(forecasts),
                            error -> showErrorMessage());

            fetchInfo();
        }
    }

    public void onRefresh() {
        if(cityId != null) {
            fetchInfo();
        }
    }

    private void fetchInfo() {
        Completable.concatArray(
                interactor.fetchAndSaveWeather(cityId),
                interactor.fetchAndSaveHourlyForecasts(cityId),
                interactor.fetchAndSaveDailyForecasts(cityId)
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> getViewState().setLoadingVisibility(false))
                .subscribe(this::showSuccessMessage,
                        error -> showErrorMessage());
    }

    private void showErrorMessage() {
        getViewState().showInfoMessage(resources.getString(R.string.info_error_loading_weather));
    }

    private void showSuccessMessage() {
        getViewState().showInfoMessage(resources.getString(R.string.info_place_updated));
    }
}
