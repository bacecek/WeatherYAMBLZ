package com.zino.mobilization.weatheryamblz.business.interactor.cities;

import com.zino.mobilization.weatheryamblz.business.Mapper;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.interactor.fetch_weather.FetchWeatherInteractorImpl;
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

public class CitiesInteractorImpl extends FetchWeatherInteractorImpl implements CitiesInteractor {
    private CitiesRepository citiesRepository;
    private SettingsManager settingsManager;
    private Mapper mapper;

    @Inject
    public CitiesInteractorImpl(WeatherRepository weatherRepository,
                         CitiesRepository citiesRepository,
                         SettingsManager settingsManager,
                         Mapper mapper) {
        super(weatherRepository, citiesRepository, mapper);
        this.citiesRepository = citiesRepository;
        this.settingsManager = settingsManager;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<City>> getCities() {
        Timber.d("get all cities");
        return settingsManager.getUnits()
                .switchMap(units -> citiesRepository.getAllCities()
                        .map(citiesEntities -> mapper.convertToCityFromEntities(citiesEntities, units)));
    }

    @Override
    public Observable<List<City>> getCitiesWithoutWeather() {
        Timber.d("get cities without weather");
        return getCities().flatMapSingle(cities -> Observable.fromIterable(cities)
                .filter(city -> city.getCurrentWeather() == null)
                .toList());
    }

    @Override
    public Completable removeCity(String cityId) {
        Timber.d("remove city: " + cityId);
        return citiesRepository.removeCity(cityId);
    }
}
