package com.zino.mobilization.weatheryamblz.business.interactor.weather;

import com.zino.mobilization.weatheryamblz.business.Mapper;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.entity.DailyForecast;
import com.zino.mobilization.weatheryamblz.business.entity.HourlyForecast;
import com.zino.mobilization.weatheryamblz.business.interactor.fetch_weather.FetchWeatherInteractorImpl;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;
import com.zino.mobilization.weatheryamblz.data.db.entity.DailyForecastEntity;
import com.zino.mobilization.weatheryamblz.data.db.entity.HourlyForecastEntity;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepository;
import com.zino.mobilization.weatheryamblz.repository.weather.WeatherRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Denis Buzmakov on 04.08.17.
 * <buzmakov.da@gmail.com>
 */

public class WeatherInteractorImpl extends FetchWeatherInteractorImpl implements WeatherInteractor {
    private CitiesRepository citiesRepository;
    private WeatherRepository weatherRepository;
    private Mapper mapper;
    private SettingsManager settingsManager;

    @Inject
    WeatherInteractorImpl(CitiesRepository citiesRepository,
                          WeatherRepository weatherRepository,
                          Mapper mapper,
                          SettingsManager settingsManager) {
        super(weatherRepository, citiesRepository, mapper);
        this.citiesRepository = citiesRepository;
        this.weatherRepository = weatherRepository;
        this.mapper = mapper;
        this.settingsManager = settingsManager;
    }

    @Override
    public Observable<City> getCity(String cityId) {
        Observable<CityEntity> city = citiesRepository.getCity(cityId);
        return settingsManager.getUnits()
                .flatMap(units ->
                        city.map(citiesEntity -> mapper.convertCityEntityToCity(citiesEntity, units)));
    }

    @Override
    public Observable<List<HourlyForecast>> getHourlyForecast(String cityId) {
        Observable<List<HourlyForecastEntity>> forecasts = weatherRepository.getHourlyForecasts(cityId);
        return settingsManager.getUnits()
                .flatMap(units ->
                    forecasts.map(list -> mapper.convertHourlyForecastsFromEntities(list, units)));
    }

    @Override
    public Observable<List<DailyForecast>> getDailyForecast(String cityId) {
        Observable<List<DailyForecastEntity>> forecasts = weatherRepository.getDailyForecasts(cityId);
        return settingsManager.getUnits()
                .flatMap(units ->
                    forecasts.map(list -> mapper.convertDailyForecastsFromEntities(list, units)));
    }
}
