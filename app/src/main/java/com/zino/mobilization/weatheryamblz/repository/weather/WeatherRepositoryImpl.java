package com.zino.mobilization.weatheryamblz.repository.weather;

import com.zino.mobilization.weatheryamblz.data.db.dao.DailyForecastDao;
import com.zino.mobilization.weatheryamblz.data.db.dao.HourlyForecastDao;
import com.zino.mobilization.weatheryamblz.data.db.entity.DailyForecastEntity;
import com.zino.mobilization.weatheryamblz.data.db.entity.HourlyForecastEntity;
import com.zino.mobilization.weatheryamblz.data.network.api.WeatherApi;
import com.zino.mobilization.weatheryamblz.data.network.response.forecast.daily.DailyForecastResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.forecast.hourly.HourlyForecastResponse;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;


public class WeatherRepositoryImpl implements WeatherRepository {
    private HourlyForecastDao hourlyForecastDao;
    private DailyForecastDao dailyForecastDao;
    private WeatherApi api;

    @Inject
    public WeatherRepositoryImpl(HourlyForecastDao hourlyForecastDao,
                                 DailyForecastDao dailyForecastDao,
                                 WeatherApi api) {
        this.hourlyForecastDao = hourlyForecastDao;
        this.dailyForecastDao = dailyForecastDao;
        this.api = api;
    }

    @Override
    public Single<WeatherResponse> getCurrentWeatherFromApi(double lat, double lon) {
        Timber.d("getting weather from api: " + lat + " " + lon);
        return api.getCurrentWeather(lat, lon);
    }

    @Override
    public Single<HourlyForecastResponse> getHourlyForecastFromApi(double lat, double lon) {
        Timber.d("getting hourly forecast from api: " + lat + " " + lon);
        return api.getHourlyForecast(lat, lon);
    }

    @Override
    public Single<DailyForecastResponse> getDailyForecastFromApi(double lat, double lon) {
        Timber.d("getting daily forecast from api: " + lat + " " + lon);
        return api.getDailyForecast(lat, lon);
    }

    @Override
    public Observable<List<HourlyForecastEntity>> getHourlyForecasts(String cityId) {
        Timber.d("getting hourly forecasts from db: " + cityId);
        return hourlyForecastDao.getForecastsByCityId(cityId)
                .toObservable();
    }

    @Override
    public Completable saveHourlyForecast(List<HourlyForecastEntity> forecasts) {
        Timber.d("saving hourly forecasts: " + forecasts);
        return Completable.fromAction(() -> hourlyForecastDao.removeForecastsByCityId(forecasts.get(0).getCityId()))
                .doOnComplete(() -> hourlyForecastDao.insert(forecasts));
    }

    @Override
    public Observable<List<DailyForecastEntity>> getDailyForecasts(String cityId) {
        Timber.d("getting daily forecasts from db: " + cityId);
        return dailyForecastDao.getForecastsByCityId(cityId)
                .toObservable();
    }

    @Override
    public Completable saveDailyForecast(List<DailyForecastEntity> forecasts) {
        Timber.d("saving daily forecasts: " + forecasts);
        return Completable.fromAction(() -> dailyForecastDao.removeForecastsByCityId(forecasts.get(0).getCityId()))
                .doOnComplete(() -> dailyForecastDao.insert(forecasts));
    }
}
