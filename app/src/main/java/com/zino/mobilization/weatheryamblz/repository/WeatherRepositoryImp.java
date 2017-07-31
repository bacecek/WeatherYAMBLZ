package com.zino.mobilization.weatheryamblz.repository;

import com.zino.mobilization.weatheryamblz.data.network.api.WeatherAPI;
import com.zino.mobilization.weatheryamblz.data.cache.cache.CacheManager;
import com.zino.mobilization.weatheryamblz.data.network.response.WeatherResponse;

import io.reactivex.Observable;
import io.reactivex.Single;


public class WeatherRepositoryImp implements WeatherRepository {

    private CacheManager cacheManager;
    private WeatherAPI api;

    public WeatherRepositoryImp(CacheManager cacheManager, WeatherAPI api) {
        this.cacheManager = cacheManager;
        this.api = api;
    }

    @Override
    public Observable<WeatherResponse> getCurrentWeather(double lat, double lon, String lang) {
        Single<WeatherResponse> fromApi = getCurrentWeatherFromApi(lat, lon, lang);
        if(cacheManager.isCacheAvailable()) {
            Single<WeatherResponse> fromCache = getCurrentWeatherFromCache();
            return Single.concat(fromCache, fromApi).toObservable();
        } else return fromApi.toObservable();
    }

    @Override
    public Single<WeatherResponse> getCurrentWeatherFromApi(double lat, double lon, String lang) {
        return api.getCurrentWeather(lat, lon, "ad0dae19ea9cd24058581481b3ce84ce", lang, "metric");
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
