package com.zino.mobilization.weatheryamblz.business.interactor.cities;

import com.zino.mobilization.weatheryamblz.business.Mapper;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.interactor.base.BaseInteractorImpl;
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

public class CitiesInteractorImpl extends BaseInteractorImpl implements CitiesInteractor {
    private WeatherRepository weatherRepository;
    private CitiesRepository citiesRepository;
    private SettingsManager settingsManager;
    private Mapper mapper;

    @Inject
    CitiesInteractorImpl(WeatherRepository weatherRepository,
                         CitiesRepository citiesRepository,
                         SettingsManager settingsManager,
                         Mapper mapper) {
        super(weatherRepository, citiesRepository, mapper);
        this.weatherRepository = weatherRepository;
        this.citiesRepository = citiesRepository;
        this.settingsManager = settingsManager;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<City>> getCities() {
        Timber.d("get all cities");
        Observable<List<CityEntity>> cities = citiesRepository.getAllCities();
        return settingsManager.getUnits()
                .flatMap(units ->
                        cities.map(citiesEntities -> mapper.convertToCityFromEntities(citiesEntities, units)));
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
    public Completable removeCity(String cityId) {
        Timber.d("remove city: " + cityId);
        return citiesRepository.removeCity(cityId);
    }
}
