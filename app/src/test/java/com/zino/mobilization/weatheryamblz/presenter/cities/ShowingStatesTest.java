package com.zino.mobilization.weatheryamblz.presenter.cities;

import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.common.TestData;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

public class ShowingStatesTest extends BaseCitiesPresenterTest {
    private List<City> citiesList = new ArrayList<>();

    @Override
    public void init() {
        super.init();
        when(citiesInteractor.getCities())
                .thenReturn(Observable.just(citiesList));
        when(citiesInteractor.getCitiesWithoutWeather())
                .thenReturn(Observable.empty());
    }

    @Test
    public void shouldShowCitiesList() {
        citiesList.add(TestData.getCity());
        citiesList.add(TestData.getCity());

        citiesPresenter.attachView(citiesView);

        verify(viewState).setCitiesListVisible(true);
        verify(viewState).setEmptyViewVisible(false);
        verify(viewState).setRefreshingEnabled(true);
    }

    @Test
    public void shouldShowEmptyView() {
        citiesList.clear();

        citiesPresenter.attachView(citiesView);

        verify(viewState).setCitiesListVisible(false);
        verify(viewState).setEmptyViewVisible(true);
        verify(viewState).setRefreshingEnabled(false);
    }
}
