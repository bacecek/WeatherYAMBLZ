package com.zino.mobilization.weatheryamblz.data.network.api;

import com.zino.mobilization.weatheryamblz.data.network.response.forecast.ForecastResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface WeatherAPI {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather")
    Single<WeatherResponse> getCurrentWeather(@Query("lat") double lat,
                                              @Query("lon") double lon,
                                              @Query("appid") String apiKey,
                                              @Query("lang") String lang,
                                              @Query("units") String units);

    @GET("forecast")
    Single<ForecastResponse> getForecast(@Query("lat") double lat,
                                         @Query("lon") double lon,
                                         @Query("units") String units);
}
