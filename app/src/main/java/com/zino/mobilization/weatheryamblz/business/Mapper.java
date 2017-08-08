package com.zino.mobilization.weatheryamblz.business;

import android.support.annotation.StringRes;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.entity.CurrentWeather;
import com.zino.mobilization.weatheryamblz.business.entity.DailyForecast;
import com.zino.mobilization.weatheryamblz.business.entity.HourlyForecast;
import com.zino.mobilization.weatheryamblz.data.db.entity.CityEntity;
import com.zino.mobilization.weatheryamblz.data.db.entity.DailyForecastEntity;
import com.zino.mobilization.weatheryamblz.data.db.entity.HourlyForecastEntity;
import com.zino.mobilization.weatheryamblz.data.db.entity.WeatherEntity;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Clouds;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Main;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Weather;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Wind;
import com.zino.mobilization.weatheryamblz.data.network.response.forecast.daily.DailyForecastItem;
import com.zino.mobilization.weatheryamblz.data.network.response.forecast.daily.DailyForecastResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.forecast.daily.TempInfo;
import com.zino.mobilization.weatheryamblz.data.network.response.forecast.hourly.HourlyForecastItem;
import com.zino.mobilization.weatheryamblz.data.network.response.forecast.hourly.HourlyForecastResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.Sys;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;
import com.zino.mobilization.weatheryamblz.utils.AppResources;
import com.zino.mobilization.weatheryamblz.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Denis Buzmakov on 06.08.17.
 * <buzmakov.da@gmail.com>
 */

public class Mapper {
    private AppResources appResources;

    @Inject
    Mapper(AppResources appResources) {
        this.appResources = appResources;
    }

    public List<City> convertToCityFromEntities(List<CityEntity> cityEntities,
                                                 boolean isCelsius) {
        List<City> list = new ArrayList<>();
        for(CityEntity cityEntity : cityEntities) {
            list.add(convertCityEntityToCity(cityEntity, isCelsius));
        }
        return list;
    }

    public City convertCityEntityToCity(CityEntity cityEntity, boolean isCelsius) {
        if(cityEntity == null) return null;

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

        return new City(cityEntity.getId(),
                cityEntity.getName(),
                cityEntity.getAddress(),
                cityEntity.getLatitude(),
                cityEntity.getLongitude(),
                currentWeather);
    }

    private String formatTemperature(double temp, boolean isCelsius) {
        @StringRes int resTemplate = isCelsius ? R.string.template_temperature_metric
                : R.string.template_temperature_imperial;
        return appResources.getString(resTemplate, Math.round(Utils.convertFromKelvinToChosenUnits(temp, isCelsius)));
    }

    public CityEntity convertCityToCityEntity(City city) {
        if(city == null) return null;

        return new CityEntity(city.getId(),
                city.getName(),
                city.getAddress(),
                city.getLatitude(),
                city.getLongitude(),
                null);
    }

    public WeatherEntity convertResponseToWeatherEntity(WeatherResponse response) {
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

    public List<HourlyForecast> convertHourlyForecastsFromEntities(List<HourlyForecastEntity> entities,
                                                                   boolean isCelsius) {
        if(entities == null) return null;

        List<HourlyForecast> list = new ArrayList<>();

        for(HourlyForecastEntity entity : entities) {
            String temperature = formatTemperature(entity.getTemperature(), isCelsius);
            String time = Utils.formatUnixTime(entity.getTime(), appResources.getString(R.string.format_hourly_forecast));
            HourlyForecast forecast = new HourlyForecast(
                    temperature,
                    time,
                    entity.getConditionId(),
                    entity.getIconId());
            list.add(forecast);
        }

        return list;
    }

    public List<DailyForecast> convertDailyForecastsFromEntities(List<DailyForecastEntity> entities,
                                                                 boolean isCelsius) {
        if(entities == null) return null;

        List<DailyForecast> list = new ArrayList<>();

        for(DailyForecastEntity entity : entities) {
            String tempDay = formatTemperature(entity.getTempDay(), isCelsius);
            String tempNight = formatTemperature(entity.getTempNight(), isCelsius);
            String day = Utils.capitalize(Utils.formatUnixTime(entity.getDate(), appResources.getString(R.string.format_daily_forecast)));
            DailyForecast forecast = new DailyForecast(
                    tempDay,
                    tempNight,
                    entity.getDescription(),
                    day,
                    entity.getConditionId(),
                    entity.getIconId());
            list.add(forecast);
        }

        return list;
    }

    public List<HourlyForecastEntity> convertHourlyForecastEntitiesFromResponse(String cityId,
                                                                                HourlyForecastResponse response) {
        if(response == null) return null;

        List<HourlyForecastEntity> entities = new ArrayList<>();

        final int maxForecastsCount = 8;
        for(int i = 0; i < maxForecastsCount; i++) {
            HourlyForecastItem item = response.getHourlyForecastItems().get(i);
            HourlyForecastEntity entity = new HourlyForecastEntity();
            entity.setCityId(cityId);
            entity.setTime(item.getTime());
            Main mainInfo = item.getMainInfo();
            if(mainInfo != null) {
                entity.setTemperature(mainInfo.getTemp());
                entity.setPressure(mainInfo.getPressure());
                entity.setHumidity(mainInfo.getHumidity());
            }
            Weather weatherInfo = item.getWeatherInfo().get(0);
            if(weatherInfo != null) {
                entity.setDescription(weatherInfo.getDescription());
                entity.setConditionId(weatherInfo.getId());
                entity.setIconId(weatherInfo.getIcon());
            }
            Wind windInfo = item.getWindInfo();
            if(windInfo != null) {
                entity.setWindSpeed(windInfo.getSpeed());
            }
            entities.add(entity);
        }

        return entities;
    }

    public List<DailyForecastEntity> convertDailyForecastEntitiesFromResponse(String cityId, DailyForecastResponse response) {
        if(response == null) return null;

        List<DailyForecastEntity> entities = new ArrayList<>();
        for(DailyForecastItem item : response.getDailyForecastItems()) {
            DailyForecastEntity entity = new DailyForecastEntity();
            entity.setCityId(cityId);
            entity.setDate(item.getTime());
            entity.setHumidity(item.getHumidity());
            entity.setPressure(item.getPressure());
            TempInfo tempInfo = item.getTempInfo();
            if(tempInfo != null) {
                entity.setTempDay(tempInfo.getTempDay());
                entity.setTempNight(tempInfo.getTempNight());
            }
            Weather weatherInfo = item.getWeatherInfo().get(0);
            if(weatherInfo != null) {
                entity.setDescription(weatherInfo.getShortDescription());
                entity.setConditionId(weatherInfo.getId());
                entity.setIconId(weatherInfo.getIcon());
            }
            entities.add(entity);
        }

        return entities;
    }
}
