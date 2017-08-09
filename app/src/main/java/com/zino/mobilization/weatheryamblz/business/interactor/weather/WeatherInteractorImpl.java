package com.zino.mobilization.weatheryamblz.business.interactor.weather;

import com.zino.mobilization.weatheryamblz.business.Mapper;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.entity.DailyForecast;
import com.zino.mobilization.weatheryamblz.business.entity.HourlyForecast;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;
import com.zino.mobilization.weatheryamblz.data.db.entity.DailyForecastEntity;
import com.zino.mobilization.weatheryamblz.data.db.entity.HourlyForecastEntity;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepository;
import com.zino.mobilization.weatheryamblz.repository.weather.WeatherRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by Denis Buzmakov on 04.08.17.
 * <buzmakov.da@gmail.com>
 */

public class WeatherInteractorImpl implements WeatherInteractor {
    private CitiesRepository citiesRepository;
    private WeatherRepository weatherRepository;
    private Mapper mapper;
    private SettingsManager settingsManager;

    @Inject
    WeatherInteractorImpl(CitiesRepository citiesRepository,
                          WeatherRepository weatherRepository,
                          Mapper mapper,
                          SettingsManager settingsManager) {
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

    @Override
    public Completable fetchAndSaveWeather(String cityId) {
        Timber.d("fetch and save weather of city: " + cityId);
        return citiesRepository.getCity(cityId)
                .firstOrError()
                .flatMap(cityEntity -> weatherRepository
                        .getCurrentWeatherFromApi(cityEntity.getLatitude(), cityEntity.getLongitude())
                        .map(mapper::convertResponseToWeatherEntity)
                        .map(weatherEntity -> new CityEntity(
                                cityEntity.getId(),
                                cityEntity.getName(),
                                cityEntity.getAddress(),
                                cityEntity.getLatitude(),
                                cityEntity.getLongitude(),
                                weatherEntity
                        ))
                ).flatMapCompletable(cityEntity -> citiesRepository.updateCity(cityEntity));
    }

    @Override
    public Completable fetchAndSaveHourlyForecasts(String cityId) {
        Timber.d("fetch and save hourly forecasts of city: " + cityId);
        return citiesRepository.getCity(cityId)
                .firstOrError()
                .flatMap(city -> weatherRepository.getHourlyForecastFromApi(city.getLatitude(), city.getLongitude())
                        .map(response -> mapper.convertHourlyForecastEntitiesFromResponse(cityId, response)))
                .flatMapCompletable(entities -> weatherRepository.saveHourlyForecast(entities));
    }

    @Override
    public Completable fetchAndSaveDailyForecasts(String cityId) {
        Timber.d("fetch and save daily forecasts of city: " + cityId);
        return citiesRepository.getCity(cityId)
                .firstOrError()
                .flatMap(city -> weatherRepository.getDailyForecastFromApi(city.getLatitude(), city.getLongitude())
                        .map(response -> mapper.convertDailyForecastEntitiesFromResponse(cityId, response)))
                .flatMapCompletable(entities -> weatherRepository.saveDailyForecast(entities));
    }
}
