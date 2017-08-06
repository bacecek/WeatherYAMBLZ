package com.zino.mobilization.weatheryamblz.repository.forecast;

import com.zino.mobilization.weatheryamblz.data.db.entity.ForecastEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Denis Buzmakov on 03.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface ForecastRepository {
    Observable<List<ForecastEntity>> getForecasts(long id);
    Completable saveForecast(List<ForecastEntity> forecasts);
}
