package com.zino.mobilization.weatheryamblz.presenter.cities;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.business.interactor.cities.CitiesInteractor;
import com.zino.mobilization.weatheryamblz.common.RxImmediateSchedulerRule;
import com.zino.mobilization.weatheryamblz.presentation.cities.CitiesPresenter;
import com.zino.mobilization.weatheryamblz.presentation.cities.CitiesView;
import com.zino.mobilization.weatheryamblz.presentation.cities.CitiesView$$State;
import com.zino.mobilization.weatheryamblz.utils.AppResources;

import org.junit.Before;
import org.junit.ClassRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Completable;
import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

public abstract class BaseCitiesPresenterTest {
    @ClassRule
    public static final RxImmediateSchedulerRule rxRule = new RxImmediateSchedulerRule();

    @Mock
    protected AppResources appResources;
    @Mock
    protected CitiesInteractor citiesInteractor;
    @Mock
    protected CitiesView$$State viewState;
    @Mock
    protected CitiesView citiesView;

    CitiesPresenter citiesPresenter;

    String placeDeleted =  "Place deleted";
    String placesUpdated =  "Places updated";
    String errorLoadingWeather =  "Failed to load weather";
    String placeAdded =  "Place added";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        citiesPresenter = new CitiesPresenter(citiesInteractor, appResources);
        citiesPresenter.setViewState(viewState);

        when(citiesInteractor.getCities())
                .thenReturn(Observable.empty());
        when(citiesInteractor.getCitiesWithoutWeather())
                .thenReturn(Observable.empty());
        when(citiesInteractor.fetchAndSaveWeather(anyString()))
                .thenReturn(Completable.complete());
        when(citiesInteractor.fetchAndSaveDailyForecasts(anyString()))
                .thenReturn(Completable.complete());
        when(citiesInteractor.fetchAndSaveHourlyForecasts(anyString()))
                .thenReturn(Completable.complete());
        when(citiesInteractor.fetchAndSaveAllCities())
                .thenReturn(Completable.complete());

        when(appResources.getString(R.string.info_place_deleted))
                .thenReturn("Place deleted");
        when(appResources.getString(R.string.info_places_updated))
                .thenReturn("Places updated");
        when(appResources.getString(R.string.info_error_loading_weather))
                .thenReturn("Failed to load weather");
        when(appResources.getString(R.string.info_place_added))
                .thenReturn("Place added");
    }
}
