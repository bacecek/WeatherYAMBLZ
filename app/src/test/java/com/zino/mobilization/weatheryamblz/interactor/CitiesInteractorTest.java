package com.zino.mobilization.weatheryamblz.interactor;

import com.zino.mobilization.weatheryamblz.business.Mapper;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.interactor.cities.CitiesInteractor;
import com.zino.mobilization.weatheryamblz.business.interactor.cities.CitiesInteractorImpl;
import com.zino.mobilization.weatheryamblz.common.RxImmediateSchedulerRule;
import com.zino.mobilization.weatheryamblz.common.TestData;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.data.settings.units.PressureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.TemperatureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.Units;
import com.zino.mobilization.weatheryamblz.data.settings.units.WindSpeedUnit;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepository;
import com.zino.mobilization.weatheryamblz.repository.weather.WeatherRepository;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 12.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CitiesInteractorTest {

    @ClassRule
    public static RxImmediateSchedulerRule rxRule;

    @Mock
    private SettingsManager settingsManager;
    @Mock
    private CitiesRepository citiesRepository;
    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private Mapper mapper;

    private CitiesInteractor interactor;
    private final City city = TestData.getCity();
    private final City cityWithoutWeather = TestData.getCityWithoutWeather();
    private final List<City> cities = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        interactor = new CitiesInteractorImpl(weatherRepository,
                citiesRepository,
                settingsManager,
                mapper);

        cities.add(city);
        cities.add(city);
        cities.add(cityWithoutWeather);
        when(settingsManager.getUnits()).thenReturn(Observable.
                just(new Units(TemperatureUnit.CELSIUS, PressureUnit.HPA, WindSpeedUnit.KMH)));

        when(citiesRepository.getAllCities()).thenReturn(Observable.just(new ArrayList<>()));
        when(mapper.convertToCityFromEntities(any(), any())).thenReturn(cities);
    }

    @Test
    public void shouldReturnAllCities() {
        interactor.getCities()
                .test()
                .assertValue(cities);

        verify(settingsManager).getUnits();
        verify(citiesRepository).getAllCities();
        verify(mapper).convertToCityFromEntities(any(), any());
    }

    @Test
    public void shouldReturnCitiesWithoutWeather() {
        List<City> cities = new ArrayList<>();
        cities.add(cityWithoutWeather);
        interactor.getCitiesWithoutWeather()
                .test()
                .assertNoErrors()
                .assertValue(cities);

        verify(settingsManager).getUnits();
        verify(citiesRepository).getAllCities();
        verify(mapper).convertToCityFromEntities(any(), any());
    }

    @Test
    public void shouldReturnComplete() {
        when(citiesRepository.removeCity(any())).thenReturn(Completable.complete());

        interactor.removeCity("kek")
                .test()
                .assertNoErrors()
                .assertComplete();

        verify(citiesRepository).removeCity("kek");
    }

    @Test
    public void shouldReturnError() {
        Throwable throwable = new Throwable();
        when(citiesRepository.removeCity(any())).thenReturn(Completable.error(throwable));

        interactor.removeCity("kek")
                .test()
                .assertError(throwable)
                .assertNotComplete();

        verify(citiesRepository).removeCity("kek");
    }

}
