package com.zino.mobilization.weatheryamblz.repository;

import com.zino.mobilization.weatheryamblz.data.network.api.WeatherApi;
import com.zino.mobilization.weatheryamblz.data.cache.cache.CacheManager;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;

import io.reactivex.Observable;
import io.reactivex.Single;


public class WeatherRepositoryImp implements WeatherRepository {

    private CacheManager cacheManager;
    private WeatherApi api;

    public WeatherRepositoryImp(CacheManager cacheManager, WeatherApi api) {
        this.cacheManager = cacheManager;
        this.api = api;
    }

    @Override
    public Observable<WeatherResponse> getCurrentWeather(double lat, double lon) {
        Single<WeatherResponse> fromApi = getCurrentWeatherFromApi(lat, lon);
        if(cacheManager.isCacheAvailable()) {
            Single<WeatherResponse> fromCache = getCurrentWeatherFromCache();
            return Single.concat(fromCache, fromApi).toObservable();
        } else return fromApi.toObservable();
    }

    @Override
    public Single<WeatherResponse> getCurrentWeatherFromApi(double lat, double lon) {
        return api.getCurrentWeather(lat, lon);
    }

    @Override
    public void saveCurrentWeather(WeatherResponse weatherResponse) {
        cacheManager.saveCurrentWeather(weatherResponse);
    }

    @Override
    public Single<WeatherResponse> getCurrentWeatherFromCache() {
        return cacheManager.getCurrentWeather();
    }
}
