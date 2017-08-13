package com.zino.mobilization.weatheryamblz.business;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.entity.CurrentWeather;
import com.zino.mobilization.weatheryamblz.business.entity.DailyForecast;
import com.zino.mobilization.weatheryamblz.business.entity.HourlyForecast;
import com.zino.mobilization.weatheryamblz.business.entity.Place;
import com.zino.mobilization.weatheryamblz.business.entity.Suggestion;
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
import com.zino.mobilization.weatheryamblz.data.network.response.places.CoordinatesInfo;
import com.zino.mobilization.weatheryamblz.data.network.response.places.PlaceInfo;
import com.zino.mobilization.weatheryamblz.data.network.response.places.PlaceResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.places.SuggestionItem;
import com.zino.mobilization.weatheryamblz.data.network.response.places.SuggestionsResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.Sys;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;
import com.zino.mobilization.weatheryamblz.data.settings.units.PressureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.TemperatureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.Units;
import com.zino.mobilization.weatheryamblz.data.settings.units.WindSpeedUnit;
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
    public Mapper(AppResources appResources) {
        this.appResources = appResources;
    }

    @Nullable
    public List<City> convertToCityFromEntities(List<CityEntity> cityEntities,
                                                 Units units) {
        if(cityEntities == null) return null;
        List<City> list = new ArrayList<>();
        for(CityEntity cityEntity : cityEntities) {
            list.add(convertCityEntityToCity(cityEntity, units));
        }
        return list;
    }

    @Nullable
    public City convertCityEntityToCity(CityEntity cityEntity, Units units) {
        if(cityEntity == null) return null;

        CurrentWeather currentWeather = null;
        WeatherEntity weatherEntity = cityEntity.getCurrentWeather();
        if(weatherEntity != null) {
            currentWeather = new CurrentWeather(
                    formatTemperature(weatherEntity.getTemperature(), units.getTemperatureUnit()),
                    weatherEntity.getDescription(),
                    appResources.getString(R.string.template_humidity, Math.round(weatherEntity.getHumidity())),
                    formatPressure(weatherEntity.getPressure(), units.getPressureUnit()),
                    Utils.formatUnixTime(
                            weatherEntity.getSunriseTime(),
                            appResources.getString(R.string.format_sunrise)
                    ),
                    Utils.formatUnixTime(
                            weatherEntity.getSunsetTime(),
                            appResources.getString(R.string.format_sunrise)
                    ),
                    formatWindSpeed(weatherEntity.getWindSpeed(), units.getWindSpeedUnit()),
                    appResources.getString(R.string.template_visibility, weatherEntity.getVisibility()),
                    appResources.getString(R.string.template_cloudiness, weatherEntity.getCloudiness()),
                    weatherEntity.getConditionId(),
                    weatherEntity.getIconId()
            );
        }

        return new City(cityEntity.getId(),
                cityEntity.getTimestamp(),
                cityEntity.getName(),
                cityEntity.getAddress(),
                cityEntity.getLatitude(),
                cityEntity.getLongitude(),
                currentWeather);
    }

    @Nullable
    public String formatTemperature(double temp, @NonNull TemperatureUnit unit) {
        switch (unit) {
            case CELSIUS: return appResources.getString(
                    R.string.template_temperature_metric, Math.round(Utils.convertKelvinToCelsius(temp)));
            case FAHRENHEIT: return appResources.getString(
                    R.string.template_temperature_imperial,
                    Math.round(Utils.convertKelvinToFahrenheit(temp)));
            default: return null;
        }
    }

    @Nullable
    public String formatPressure(double pressure, @NonNull PressureUnit unit) {
        switch (unit) {
            case HPA: return appResources.getString(R.string.template_pressure_hpa, Math.round(pressure));
            case MMHG: return appResources.getString(
                    R.string.template_pressure_mmhg,
                    Math.round(Utils.convertHpaToMmhg(pressure)));
            default: return null;
        }
    }

    @Nullable
    public String formatWindSpeed(double windSpeed, @NonNull WindSpeedUnit unit) {
        switch (unit) {
            case MS: return appResources.getString(R.string.template_wind_speed_ms, Math.round(windSpeed));
            case KMH: return appResources.getString(
                    R.string.template_wind_speed_kmh,
                    Math.round(Utils.convertMsToKmh(windSpeed)));
            default: return null;
        }
    }

    @Nullable
    public CityEntity convertCityToCityEntity(City city) {
        if(city == null) return null;

        return new CityEntity(city.getId(),
                city.getTimestamp(),
                city.getName(),
                city.getAddress(),
                city.getLatitude(),
                city.getLongitude(),
                null);
    }

    @Nullable
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
        List<Weather> weatherList = response.getWeather();
        if(weatherList != null) {
            Weather weatherInfo = response.getWeather().get(0);
            if (weatherInfo != null) {
                weatherEntity.setDescription(weatherInfo.getDescription());
                weatherEntity.setIconId(weatherInfo.getIcon());
                weatherEntity.setConditionId(weatherInfo.getId());
            }
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
        Timber.d("convert response to weather entity: %s", weatherEntity.toString());
        return weatherEntity;
    }

    @Nullable
    public List<HourlyForecast> convertHourlyForecastsFromEntities(List<HourlyForecastEntity> entities,
                                                                   Units units) {
        if(entities == null) return null;

        List<HourlyForecast> list = new ArrayList<>();

        for(HourlyForecastEntity entity : entities) {
            String temperature = formatTemperature(entity.getTemperature(), units.getTemperatureUnit());
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

    @Nullable
    public List<DailyForecast> convertDailyForecastsFromEntities(List<DailyForecastEntity> entities,
                                                                 Units units) {
        if(entities == null) return null;

        List<DailyForecast> list = new ArrayList<>();

        for(DailyForecastEntity entity : entities) {
            String tempDay = formatTemperature(entity.getTempDay(), units.getTemperatureUnit());
            String tempNight = formatTemperature(entity.getTempNight(), units.getTemperatureUnit());
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

    @Nullable
    public List<HourlyForecastEntity> convertHourlyForecastEntitiesFromResponse(String cityId,
                                                                                HourlyForecastResponse response) {
        if(response == null) return null;

        List<HourlyForecastEntity> entities = new ArrayList<>();

        for(HourlyForecastItem item : response.getHourlyForecastItems()) {
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

    @Nullable
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

    @Nullable
    public List<Suggestion> convertSuggestionsFromResponse(SuggestionsResponse response) {
        if(response == null || response.getSuggestions() == null) return null;

        List<Suggestion> suggestions = new ArrayList<>();
        for(SuggestionItem suggestionItem : response.getSuggestions()) {
            if(suggestionItem == null) continue;

            Suggestion suggestion = new Suggestion(suggestionItem.getPlaceId(), suggestionItem.getDescription());

            suggestions.add(suggestion);
        }

        return suggestions;
    }

    @Nullable
    public Place convertPlaceFromResponse(PlaceResponse response) {
        if(response == null
                || response.getPlaceInfo() == null
                || response.getPlaceInfo().getLocationInfo() == null
                || response.getPlaceInfo().getLocationInfo().getCoordinatesInfo() == null) return null;

        PlaceInfo placeInfo = response.getPlaceInfo();
        CoordinatesInfo coordinatesInfo = placeInfo.getLocationInfo().getCoordinatesInfo();

        return new Place(placeInfo.getId(),
                placeInfo.getName(),
                placeInfo.getAddress(),
                coordinatesInfo.getLatitude(),
                coordinatesInfo.getLongitude());
    }

    @Nullable
    public CityEntity convertPlaceToCityEntity(Place place) {
        if(place == null) return null;

        return new CityEntity(place.getId(),
                System.currentTimeMillis(),
                place.getName(),
                place.getAddress(),
                place.getLatitude(),
                place.getLongitude(),
                null);
    }
}
