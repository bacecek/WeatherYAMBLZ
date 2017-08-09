package com.zino.mobilization.weatheryamblz.business.interactor.weather;

import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.entity.DailyForecast;
import com.zino.mobilization.weatheryamblz.business.entity.HourlyForecast;
import com.zino.mobilization.weatheryamblz.business.interactor.base.BaseInteractor;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Denis Buzmakov on 04.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface WeatherInteractor extends BaseInteractor{
    Observable<City> getCity(String cityId);
    Observable<List<HourlyForecast>> getHourlyForecast(String cityId);
    Observable<List<DailyForecast>> getDailyForecast(String cityId);
}
