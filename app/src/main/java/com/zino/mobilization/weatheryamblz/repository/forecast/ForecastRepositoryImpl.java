package com.zino.mobilization.weatheryamblz.repository.forecast;

import com.zino.mobilization.weatheryamblz.data.db.dao.ForecastDao;
import com.zino.mobilization.weatheryamblz.data.db.entity.ForecastEntity;
import com.zino.mobilization.weatheryamblz.data.network.api.WeatherApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Denis Buzmakov on 03.08.17.
 * <buzmakov.da@gmail.com>
 */

public class ForecastRepositoryImpl implements ForecastRepository {
    private ForecastDao forecastDao;
    private WeatherApi weatherApi;

    @Inject
    public ForecastRepositoryImpl(ForecastDao forecastDao, WeatherApi weatherApi) {
        this.forecastDao = forecastDao;
        this.weatherApi = weatherApi;
    }

    @Override
    public Observable<List<ForecastEntity>> getForecasts(long id) {
        return forecastDao.getForecastsByCityId(id)
                .toObservable();
    }

    @Override
    public Completable saveForecast(List<ForecastEntity> forecasts) {
        return Completable.fromAction(() -> forecastDao.insert(forecasts));
    }
}
