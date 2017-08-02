package com.zino.mobilization.weatheryamblz.data.network.api;

import com.zino.mobilization.weatheryamblz.data.network.response.forecast.ForecastResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface WeatherApi {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    String API_KEY = "ad0dae19ea9cd24058581481b3ce84ce";

    @GET("weather")
    Single<WeatherResponse> getCurrentWeather(@Query("lat") double lat,
                                              @Query("lon") double lon);

    @GET("forecast")
    Single<ForecastResponse> getForecast(@Query("lat") double lat,
                                         @Query("lon") double lon);
}
