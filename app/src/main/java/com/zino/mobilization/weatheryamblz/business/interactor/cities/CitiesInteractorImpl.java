package com.zino.mobilization.weatheryamblz.business.interactor.cities;

import com.zino.mobilization.weatheryamblz.business.Mapper;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepository;
import com.zino.mobilization.weatheryamblz.repository.weather.WeatherRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by Denis Buzmakov on 03.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CitiesInteractorImpl implements CitiesInteractor {
    private WeatherRepository weatherRepository;
    private CitiesRepository citiesRepository;
    private SettingsManager settingsManager;
    private Mapper mapper;

    @Inject
    CitiesInteractorImpl(WeatherRepository weatherRepository,
                         CitiesRepository citiesRepository,
                         SettingsManager settingsManager,
                         Mapper mapper) {
        this.weatherRepository = weatherRepository;
        this.citiesRepository = citiesRepository;
        this.settingsManager = settingsManager;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<City>> getCities() {
        Timber.d("get all cities");
        Observable<List<CityEntity>> cities = citiesRepository.getAllCities();
        return settingsManager.isCelsius()
                .flatMap(isCelsius ->
                        cities.map(citiesEntities -> mapper.convertToCityFromEntities(citiesEntities, isCelsius)));
    }

    @Override
    public Observable<List<City>> getCitiesWithoutWeather() {
        Timber.d("get cities without weather");
        return getCities().flatMapSingle(cities -> Observable.fromIterable(cities)
                .filter(city -> city.getCurrentWeather() == null)
                .toList());
    }

    @Override
    public Completable addCity(City city) {
        Timber.d("add city: " + city);
        return citiesRepository.addCity(mapper.convertCityToCityEntity(city));
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
    public Completable removeCity(String cityId) {
        Timber.d("remove city: " + cityId);
        return citiesRepository.removeCity(cityId);
    }
}
