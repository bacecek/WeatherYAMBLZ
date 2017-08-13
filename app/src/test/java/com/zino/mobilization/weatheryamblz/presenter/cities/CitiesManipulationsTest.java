package com.zino.mobilization.weatheryamblz.presenter.cities;

import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.common.TestData;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CitiesManipulationsTest extends BaseCitiesPresenterTest{

    @Test
    public void shouldOpenAddCity() {
        citiesPresenter.onClickAddCity();

        verify(viewState, only()).openChooseCity();
    }

    @Test
    public void shouldDeleteCity() {
        when(citiesInteractor.removeCity(anyString()))
                .thenReturn(Completable.complete());

        citiesPresenter.onSwipeCity(TestData.getCity());

        verify(citiesInteractor).removeCity(TestData.getCity().getId());
        verify(viewState).showInfoMessage(placeDeleted);
    }

    @Test
    public void shouldShowPlaceAdded() {
        List<City> citiesWithoutWeather = new ArrayList<>();
        citiesWithoutWeather.add(TestData.getCityWithoutWeather());

        when(citiesInteractor.getCitiesWithoutWeather())
                .thenReturn(Observable.just(citiesWithoutWeather));

        citiesPresenter.attachView(viewState);

        verify(citiesInteractor).getCitiesWithoutWeather();
        verify(viewState).showInfoMessage(placeAdded);
    }

    @Test
    public void shouldFetchAllCitiesOnRefresh() {
        citiesPresenter.onRefresh();

        verify(citiesInteractor).fetchAndSaveAllCities();
        verify(viewState).setLoadingVisible(false);
        verify(viewState).showInfoMessage(placesUpdated);
    }

    @Test
    public void shouldShowErrorOnFailRefresh() {
        when(citiesInteractor.fetchAndSaveAllCities())
                .thenReturn(Completable.error(new RuntimeException()));

        citiesPresenter.onRefresh();

        verify(citiesInteractor).fetchAndSaveAllCities();
        verify(viewState).setLoadingVisible(false);
        verify(viewState).showInfoMessage(errorLoadingWeather);
    }

}
