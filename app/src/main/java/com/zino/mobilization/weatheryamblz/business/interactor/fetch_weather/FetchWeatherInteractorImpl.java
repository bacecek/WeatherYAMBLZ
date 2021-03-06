package com.zino.mobilization.weatheryamblz.business.interactor.fetch_weather;

import com.zino.mobilization.weatheryamblz.business.Mapper;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepository;
import com.zino.mobilization.weatheryamblz.repository.weather.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public class FetchWeatherInteractorImpl implements FetchWeatherInteractor {
    private WeatherRepository weatherRepository;
    private CitiesRepository citiesRepository;
    private Mapper mapper;

    @Inject
    public FetchWeatherInteractorImpl(WeatherRepository weatherRepository, CitiesRepository citiesRepository, Mapper mapper) {
        this.weatherRepository = weatherRepository;
        this.citiesRepository = citiesRepository;
        this.mapper = mapper;
    }

    @Override
    public Completable fetchAndSaveWeather(String cityId) {
        Timber.d("fetch and save weather of city: %s", cityId);
        return citiesRepository.getCity(cityId)
                .firstOrError()
                .flatMap(cityEntity -> weatherRepository
                        .getCurrentWeatherFromApi(cityEntity.getLatitude(), cityEntity.getLongitude())
                        .map(mapper::convertResponseToWeatherEntity)
                        .map(weatherEntity -> new CityEntity(
                                cityEntity.getId(),
                                cityEntity.getTimestamp(),
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
        Timber.d("fetch and save hourly forecasts of city: %s", cityId);
        return citiesRepository.getCity(cityId)
                .firstOrError()
                .flatMap(city -> weatherRepository.getHourlyForecastFromApi(city.getLatitude(), city.getLongitude())
                        .map(response -> mapper.convertHourlyForecastEntitiesFromResponse(cityId, response)))
                .flatMapCompletable(entities -> weatherRepository.saveHourlyForecast(entities));
    }

    @Override
    public Completable fetchAndSaveDailyForecasts(String cityId) {
        Timber.d("fetch and save daily forecasts of city: %s", cityId);
        return citiesRepository.getCity(cityId)
                .firstOrError()
                .flatMap(city -> weatherRepository.getDailyForecastFromApi(city.getLatitude(), city.getLongitude())
                        .map(response -> mapper.convertDailyForecastEntitiesFromResponse(cityId, response)))
                .flatMapCompletable(entities -> weatherRepository.saveDailyForecast(entities));
    }

    @Override
    public Completable fetchAndSaveAllCities() {
        Timber.d("fetch and save all cities");
        return citiesRepository.getAllCities().firstOrError()
                .flatMapCompletable(cityEntities -> Observable.fromIterable(cityEntities)
                        .flatMapCompletable(cityEntity -> fetchAndSaveWeather(cityEntity.getId())));
    }
}
