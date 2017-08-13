package com.zino.mobilization.weatheryamblz.common;

import com.google.gson.Gson;
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
import com.zino.mobilization.weatheryamblz.data.network.response.forecast.daily.DailyForecastResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.forecast.hourly.HourlyForecastResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.places.SuggestionsResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis Buzmakov on 29.07.17.
 * <buzmakov.da@gmail.com>
 */

public class TestData {

    public static WeatherResponse getCorrectWeatherResponse() {
        return new Gson().fromJson(TestJsons.getMoscowWeatherJson(), WeatherResponse.class);
    }

    public static WeatherResponse getIncorrectWeatherResponse() {
        return new Gson().fromJson(TestJsons.getPermWeatherJson(), WeatherResponse.class);
    }

    public static SuggestionsResponse getCorrectSuggestionsReponse() {
        return new Gson().fromJson(TestJsons.getCorrectSuggestionsJson(), SuggestionsResponse.class);
    }

    public static SuggestionsResponse getEmptySuggestionsResponse() {
        return new Gson().fromJson(TestJsons.getEmptySuggestionsJson(), SuggestionsResponse.class);
    }

    public static HourlyForecastResponse getHourlyForecastResponse() {
        return new Gson().fromJson(TestJsons.getHourlyForecastJson(), HourlyForecastResponse.class);
    }

    public static DailyForecastResponse getDailyForecastResponse() {
        return new Gson().fromJson(TestJsons.getDailyForecastJson(), DailyForecastResponse.class);
    }

    public static CityEntity getCityEntity() {
        return new CityEntity("afvnajdfjv",
                143513456,
                "Mosckw",
                "Mosckw, Rssia",
                33.3,
                66.6,
                getCorrectWeatherEntity());
    }

    public static City getCity() {
        return new City("afvnajdfjv",
                143513456,
                "Mosckw",
                "Mosckw, Rssia",
                33.3,
                66.6,
                getCurrentWeather()
        );
    }

    public static City getCityWithoutWeather() {
        return new City("afvnajdfjv",
                143513456,
                "Mosckw",
                "Mosckw, Rssia",
                33.3,
                66.6,
                null
        );
    }

    public static CurrentWeather getCurrentWeather() {
        return new CurrentWeather(
                "300.91",
                "scattered clouds",
                "51",
                "1007",
                "1501177452",
                "1501118798",
                "6",
                "10000",
                "40",
                802,
                "03d"
        );
    }

    public static Suggestion getCorrectSuggestion() {
        return new Suggestion("ChIJpTvG15DL1IkRd8S0KlBVNTI", "Toronto, ON, Canada");
    }

    public static List<Suggestion> getCorrectSuggestions() {
        List<Suggestion> list = new ArrayList<>();

        Suggestion suggestion = new Suggestion("ChIJpTvG15DL1IkRd8S0KlBVNTI", "Toronto, ON, Canada");
        list.add(suggestion);
        suggestion = new Suggestion("ChIJ4dG5s4K3wogRY7SWr4kTX6c", "Tampa, FL, United States");
        list.add(suggestion);
        suggestion = new Suggestion("ChIJK-0sC0Fl1oYRFccWTTgtw3M", "Tucson, AZ, United States");
        list.add(suggestion);
        suggestion = new Suggestion("ChIJJb4YZBJtiEcRv3ec1gP4A4k", "Torino, TO, Italia");
        list.add(suggestion);
        suggestion = new Suggestion("ChIJ_1J17G-7rhIRMBBBL5z2BgQ", "Toulouse, France");
        list.add(suggestion);

        return list;
    }

    public static WeatherEntity getCorrectWeatherEntity() {
        WeatherEntity entity = new WeatherEntity();
        entity.setTemperature(300.91);
        entity.setPressure(1007);
        entity.setHumidity(51);
        entity.setCloudiness(40);
        entity.setWindSpeed(6);
        entity.setVisibility(10000);
        entity.setConditionId(802);
        entity.setIconId("03d");
        entity.setSunsetTime(1501177452);
        entity.setSunriseTime(1501118798);
        entity.setDescription("scattered clouds");
        return entity;
    }

    public static WeatherEntity getIncorrectWeatherEntity() {
        WeatherEntity entity = new WeatherEntity();
        entity.setTemperature(294.845);
        entity.setPressure(1001.94);
        entity.setHumidity(78);
        entity.setCloudiness(8);
        entity.setWindSpeed(2.47);
        return entity;
    }

    public static Place getCorrectPlace() {
        return new Place("afvnajdfjv",
                "Mosckw",
                "Mosckw, Rssia",
                33.3,
                66.6);
    }

    public static List<HourlyForecastEntity> getHourlyForecastEntities() {
        List<HourlyForecastEntity> list = new ArrayList<>();
        HourlyForecastEntity hourlyForecast = new HourlyForecastEntity();
        hourlyForecast.setCityId("01");
        hourlyForecast.setHumidity(15);
        hourlyForecast.setTime(14525469);
        hourlyForecast.setTemperature(33);
        list.add(hourlyForecast);
        list.add(hourlyForecast);
        return list;
    }

    public static List<DailyForecastEntity> getDailyForecastEntities() {
        List<DailyForecastEntity> list = new ArrayList<>();
        DailyForecastEntity dailyForecastEntity = new DailyForecastEntity();
        dailyForecastEntity.setCityId("01");
        dailyForecastEntity.setHumidity(15);
        dailyForecastEntity.setPressure(1009);
        list.add(dailyForecastEntity);
        list.add(dailyForecastEntity);
        return list;
    }

    public static List<HourlyForecast> getHourlyForecast() {
        List<HourlyForecast> list = new ArrayList<>();
        HourlyForecast hourlyForecast = new HourlyForecast("31", "5:00", 800, "03d");
        list.add(hourlyForecast);
        list.add(hourlyForecast);
        return list;
    }

    public static List<DailyForecast> getDailyForecast() {
        List<DailyForecast> list = new ArrayList<>();
        DailyForecast dailyForecast = new DailyForecast("31", "31", "rain", "Moscow", 800, "03d");
        list.add(dailyForecast);
        list.add(dailyForecast);
        return list;
    }

}
