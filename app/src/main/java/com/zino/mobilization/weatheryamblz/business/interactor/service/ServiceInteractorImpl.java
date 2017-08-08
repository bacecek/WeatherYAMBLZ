package com.zino.mobilization.weatheryamblz.business.interactor.service;

import com.zino.mobilization.weatheryamblz.business.Mapper;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepository;
import com.zino.mobilization.weatheryamblz.repository.weather.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by Denis Buzmakov on 06.08.17.
 * <buzmakov.da@gmail.com>
 */

public class ServiceInteractorImpl implements ServiceInteractor {
    private CitiesRepository citiesRepository;
    private WeatherRepository weatherRepository;
    private Mapper mapper;

    @Inject
    ServiceInteractorImpl(CitiesRepository citiesRepository,
                          WeatherRepository weatherRepository,
                          Mapper mapper) {
        this.citiesRepository = citiesRepository;
        this.weatherRepository = weatherRepository;
        this.mapper = mapper;
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
    public Completable fetchAndSaveAllCities() {
        Timber.d("fetch and save all cities");
        return citiesRepository.getAllCities().firstOrError()
                .flatMapCompletable(cityEntities -> Observable.fromIterable(cityEntities)
                        .flatMapCompletable(cityEntity -> fetchAndSaveWeather(cityEntity.getId())));
    }
}
