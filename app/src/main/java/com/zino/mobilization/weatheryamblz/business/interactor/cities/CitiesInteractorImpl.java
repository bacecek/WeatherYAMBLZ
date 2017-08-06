package com.zino.mobilization.weatheryamblz.business.interactor.cities;

import android.support.annotation.StringRes;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.entity.CurrentWeather;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;
import com.zino.mobilization.weatheryamblz.data.db.entity.WeatherEntity;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Clouds;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Main;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Weather;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Wind;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.Sys;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.repository.city.CitiesRepository;
import com.zino.mobilization.weatheryamblz.repository.weather.WeatherRepository;
import com.zino.mobilization.weatheryamblz.utils.AppResources;
import com.zino.mobilization.weatheryamblz.utils.Utils;

import java.util.ArrayList;
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
    private AppResources appResources;
    private SettingsManager settingsManager;

    @Inject
    public CitiesInteractorImpl(WeatherRepository weatherRepository,
                                CitiesRepository citiesRepository,
                                AppResources appResources,
                                SettingsManager settingsManager) {
        this.weatherRepository = weatherRepository;
        this.citiesRepository = citiesRepository;
        this.appResources = appResources;
        this.settingsManager = settingsManager;
    }

    @Override
    public Observable<List<City>> getCities() {
        Timber.d("get all cities");
        Observable<List<CityEntity>> cities = citiesRepository.getAllCities();
        return settingsManager.isCelsius()
                .flatMap(isCelsius ->
                        cities.map(citiesEntities -> convertToCityFromEntities(citiesEntities, isCelsius)));
    }

    @Override
    public Observable<List<City>> getCitiesWithoutWeather() {
        return getCities().flatMapSingle(cities -> Observable.fromIterable(cities)
                .filter(city -> city.getCurrentWeather() == null)
                .toList());
    }

    @Override
    public Completable addCity(City city) {
        Timber.d("add city: " + city);
        return citiesRepository.addCity(convertCityToCityEntity(city));
    }

    @Override
    public Completable fetchAndSaveWeather(String cityId) {
        Timber.d("fetch and save weather of city: " + cityId);
        return citiesRepository.getCity(cityId)
                        .flatMap(cityEntity -> weatherRepository
                                .getCurrentWeatherFromApi(cityEntity.getLatitude(), cityEntity.getLongitude())
                                .map(this::convertResponseToWeatherEntity)
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

    @Override
    public Completable removeCity(String cityId) {
        Timber.d("remove city: " + cityId);
        return citiesRepository.removeCity(cityId);
    }

    private List<City> convertToCityFromEntities(List<CityEntity> cityEntities,
                                                 boolean isCelsius) {
        List<City> list = new ArrayList<>();
        for(CityEntity cityEntity : cityEntities) {
            CurrentWeather currentWeather = null;
            WeatherEntity weatherEntity = cityEntity.getCurrentWeather();
            if(weatherEntity != null) {
                currentWeather = new CurrentWeather(
                        formatTemperature(weatherEntity.getTemperature(), isCelsius),
                        weatherEntity.getDescription(),
                        appResources.getString(R.string.template_humidity, weatherEntity.getHumidity()),
                        appResources.getString(R.string.template_pressure, weatherEntity.getPressure()),
                        Utils.formatUnixTime(
                                weatherEntity.getSunriseTime(),
                                appResources.getString(R.string.format_sunrise)
                        ),
                        Utils.formatUnixTime(
                                weatherEntity.getSunsetTime(),
                                appResources.getString(R.string.format_sunrise)
                        ),
                        appResources.getString(R.string.template_wind_speed, weatherEntity.getWindSpeed()),
                        appResources.getString(R.string.template_visibility, weatherEntity.getVisibility()),
                        appResources.getString(R.string.template_cloudiness, weatherEntity.getCloudiness()),
                        weatherEntity.getConditionId(),
                        weatherEntity.getIconId()
                );
            }

            City city = new City(cityEntity.getId(),
                    cityEntity.getName(),
                    cityEntity.getAddress(),
                    cityEntity.getLatitude(),
                    cityEntity.getLongitude(),
                    currentWeather);

            list.add(city);
        }
        return list;
    }

    private String formatTemperature(double temp, boolean isCelsius) {
        @StringRes int resTemplate = isCelsius ? R.string.template_temperature_metric
                : R.string.template_temperature_imperial;
        return appResources.getString(resTemplate, Math.round(Utils.convertFromKelvinToChosenUnits(temp, isCelsius)));
    }

    private CityEntity convertCityToCityEntity(City city) {
        if(city == null) return null;

        return new CityEntity(city.getId(),
                city.getName(),
                city.getAddress(),
                city.getLatitude(),
                city.getLongitude(),
                null);
    }

    private WeatherEntity convertResponseToWeatherEntity(WeatherResponse response) {
        if(response == null) return null;

        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setVisibility(response.getVisibility());
        Main mainInfo = response.getMain();
        if(mainInfo != null) {
            weatherEntity.setTemperature(mainInfo.getTemp());
            weatherEntity.setHumidity(mainInfo.getHumidity());
            weatherEntity.setPressure(mainInfo.getPressure());
        }
        Weather weatherInfo = response.getWeather().get(0);
        if(weatherInfo != null) {
            weatherEntity.setDescription(weatherInfo.getDescription());
            weatherEntity.setIconId(weatherInfo.getIcon());
            weatherEntity.setConditionId(weatherInfo.getId());
        }
        Sys sysInfo = response.getSys();
        if(sysInfo != null) {
            weatherEntity.setSunriseTime(sysInfo.getSunrise());
            weatherEntity.setSunsetTime(sysInfo.getSunset());
        }
        Wind windInfo = response.getWind();
        if(windInfo != null) {
            weatherEntity.setWindSpeed(windInfo.getSpeed());
        }
        Clouds cloudsInfo = response.getClouds();
        if(cloudsInfo != null) {
            weatherEntity.setCloudiness(cloudsInfo.getAll());
        }
        Timber.d("convert response to weather entity: " + weatherEntity.toString());
        return weatherEntity;
    }
}
