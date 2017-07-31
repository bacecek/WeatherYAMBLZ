package com.zino.mobilization.weatheryamblz.presentation.weather;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.zino.mobilization.weatheryamblz.data.cache.pojo.City;
import com.zino.mobilization.weatheryamblz.data.network.response.WeatherResponse;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface WeatherView extends MvpView {

    void showWeather(WeatherResponse weatherResponse);

    void showLoading();

    void hideLoading();

    void setCelsius(boolean celsius);

    void showCity(City city);

}
