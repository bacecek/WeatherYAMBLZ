package com.zino.mobilization.weatheryamblz.repository.weather;

import com.zino.mobilization.weatheryamblz.data.db.dao.WeatherDao;
import com.zino.mobilization.weatheryamblz.data.db.entity.WeatherEntity;
import com.zino.mobilization.weatheryamblz.data.network.api.WeatherApi;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;


public class WeatherRepositoryImpl implements WeatherRepository {

    //private WeatherDao weatherDao;
    private WeatherApi api;

    @Inject
    public WeatherRepositoryImpl(/*WeatherDao weatherDao, */WeatherApi api) {
        //this.weatherDao = weatherDao;
        this.api = api;
    }

    @Override
    public Single<WeatherResponse> getCurrentWeatherFromApi(double lat, double lon) {
        Timber.d("get weather from api: " + lat + " " + lon);
        return api.getCurrentWeather(lat, lon);
    }

//    @Override
//    public Observable<List<WeatherEntity>> getAllCurrentWeathers() {
//        Timber.d("get all current weathers");
//        return weatherDao.getAllWeather()
//                .toObservable();
//    }

    /*@Override
    public Observable<WeatherEntity> getCurrentWeatherFromDb(String cityId) {
        Timber.d("get current weather from db: " + cityId);
        return weatherDao.getWeatherByCityId(cityId)
                .toObservable();
    }*/
}
