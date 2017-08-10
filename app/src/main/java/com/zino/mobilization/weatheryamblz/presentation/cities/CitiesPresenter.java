package com.zino.mobilization.weatheryamblz.presentation.cities;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.interactor.cities.CitiesInteractor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

@InjectViewState
public class CitiesPresenter extends MvpPresenter<CitiesView> {
    private CitiesInteractor interactor;

    public CitiesPresenter(CitiesInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        interactor.getCities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(cities -> {
                    getViewState().setCitiesListVisible(cities.size() != 0);
                    getViewState().setEmptyViewVisible(cities.size() == 0);
                })
                .subscribe(this::showCities);

        interactor.getCitiesWithoutWeather()
                .flatMapCompletable(cities -> Observable.fromIterable(cities)
                        .flatMapCompletable(city -> Completable.concatArray(
                                interactor.fetchAndSaveWeather(city.getId()),
                                interactor.fetchAndSaveDailyForecasts(city.getId()),
                                interactor.fetchAndSaveHourlyForecasts(city.getId())))
                ).subscribe();
    }

    public void onClickAddCity() {
        getViewState().openChooseCity();
    }

    public void onSwipeCity(City city) {
        Timber.d("on swipe city: " + city.toString());
        interactor.removeCity(city.getId())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void showCities(List<City> cities) {
        Timber.d("show cities on view: " + cities.toString());
        getViewState().updateCities(cities);
    }
}
