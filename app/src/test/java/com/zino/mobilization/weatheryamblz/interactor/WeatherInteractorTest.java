package com.zino.mobilization.weatheryamblz.interactor;

import com.zino.mobilization.weatheryamblz.business.Mapper;
import com.zino.mobilization.weatheryamblz.business.interactor.weather.WeatherInteractor;
import com.zino.mobilization.weatheryamblz.business.interactor.weather.WeatherInteractorImpl;
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

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

public class WeatherInteractorTest {
    @ClassRule
    public static final RxImmediateSchedulerRule rxRule = new RxImmediateSchedulerRule();

    @Mock
    private SettingsManager settingsManager;
    @Mock
    private CitiesRepository citiesRepository;
    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private Mapper mapper;

    private WeatherInteractor weatherInteractor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        weatherInteractor = new WeatherInteractorImpl(citiesRepository,
                weatherRepository,
                mapper,
                settingsManager);

        when(settingsManager.getUnits()).thenReturn(Observable.
                just(new Units(TemperatureUnit.CELSIUS, PressureUnit.HPA, WindSpeedUnit.KMH)));
    }

    @Test
    public void shouldReturnCity() {
        when(citiesRepository.getCity(anyString()))
                .thenReturn(Observable.just(TestData.getCityEntity()));

        when(mapper.convertCityEntityToCity(any(), any()))
                .thenReturn(TestData.getCity());

        weatherInteractor.getCity("kke")
                .test()
                .assertNoErrors()
                .assertComplete();

        verify(citiesRepository).getCity("kke");
        verify(mapper).convertCityEntityToCity(any(), any());
    }

    @Test
    public void shouldReturnHourlyForecast() {
        when(weatherRepository.getHourlyForecasts(anyString()))
                .thenReturn(Observable.just(TestData.getHourlyForecastEntities()));

        when(mapper.convertHourlyForecastsFromEntities(any(), any()))
                .thenReturn(TestData.getHourlyForecast());

        weatherInteractor.getHourlyForecast("kke")
                .test()
                .assertNoErrors()
                .assertComplete();

        verify(weatherRepository).getHourlyForecasts("kke");
        verify(mapper).convertHourlyForecastsFromEntities(any(), any());
    }

    @Test
    public void shouldReturnDailyForecast() {
        when(weatherRepository.getDailyForecasts(anyString()))
                .thenReturn(Observable.just(TestData.getDailyForecastEntities()));

        when(mapper.convertDailyForecastsFromEntities(any(), any()))
                .thenReturn(TestData.getDailyForecast());

        weatherInteractor.getDailyForecast("kke")
                .test()
                .assertNoErrors()
                .assertComplete();

        verify(weatherRepository).getDailyForecasts("kke");
        verify(mapper).convertDailyForecastsFromEntities(any(), any());
    }
}
