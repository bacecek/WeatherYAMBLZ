package com.zino.mobilization.weatheryamblz.data.network.api;

import com.zino.mobilization.weatheryamblz.data.network.response.forecast.daily.DailyForecastResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.forecast.hourly.HourlyForecastResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather")
    Single<WeatherResponse> getCurrentWeather(@Query("lat") double lat,
                                              @Query("lon") double lon);

    @GET("forecast?cnt=8")
    Single<HourlyForecastResponse> getHourlyForecast(@Query("lat") double lat,
                                                     @Query("lon") double lon);

    @GET("forecast/daily?cnt=5")
    Single<DailyForecastResponse> getDailyForecast(@Query("lat") double lat,
                                                   @Query("lon") double lon);
}
