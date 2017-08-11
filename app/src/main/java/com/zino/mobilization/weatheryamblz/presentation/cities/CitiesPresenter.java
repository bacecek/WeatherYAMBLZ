package com.zino.mobilization.weatheryamblz.presentation.cities;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.interactor.cities.CitiesInteractor;
import com.zino.mobilization.weatheryamblz.utils.AppResources;

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
    private AppResources resources;

    public CitiesPresenter(CitiesInteractor interactor, AppResources resources) {
        this.interactor = interactor;
        this.resources = resources;
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
                    getViewState().setRefreshingEnabled(cities.size() != 0);
                })
                .subscribe(this::showCities);

        interactor.getCitiesWithoutWeather()
                .flatMapCompletable(cities -> Observable.fromIterable(cities)
                        .doOnNext(__ -> getViewState().showInfoMessage(resources.getString(R.string.info_place_added)))
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
                .doOnComplete(() -> getViewState().showInfoMessage(resources.getString(R.string.info_place_deleted)))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void onRefresh() {
        interactor.fetchAndSaveAllCities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally(() -> getViewState().setLoadingVisible(false))
                .subscribe(() -> getViewState().showInfoMessage(resources.getString(R.string.info_places_updated)),
                        __ -> getViewState().showInfoMessage(resources.getString(R.string.info_error_loading_weather)));
    }

    private void showCities(List<City> cities) {
        Timber.d("show cities on view: " + cities.toString());
        getViewState().updateCities(cities);
    }
}
