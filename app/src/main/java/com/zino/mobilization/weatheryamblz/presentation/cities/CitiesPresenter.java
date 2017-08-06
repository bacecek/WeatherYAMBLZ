package com.zino.mobilization.weatheryamblz.presentation.cities;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.location.places.Place;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.interactor.cities.CitiesInteractor;

import java.util.List;

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
                        .flatMapCompletable(city -> interactor.fetchAndSaveWeather(city.getId()))
                ).subscribe();
    }

    public void onClickAddCity() {
        getViewState().openChooseCity();
    }

    public void onCityChosen(Place place) {
        if(place == null) return;
        City city = new City(
                place.getId(),
                place.getName().toString(),
                place.getAddress().toString(),
                place.getLatLng().latitude,
                place.getLatLng().longitude,
                null
        );
        interactor.addCity(city)
                .subscribeOn(Schedulers.io())
                .subscribe();
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
