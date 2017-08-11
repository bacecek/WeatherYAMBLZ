package com.zino.mobilization.weatheryamblz.presentation.weather;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.entity.DailyForecast;
import com.zino.mobilization.weatheryamblz.business.entity.HourlyForecast;

import java.util.List;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface WeatherView extends MvpView {
    void setLoadingVisible(boolean visible);
    void showCity(@NonNull City city);
    void showHourlyForecasts(@NonNull List<HourlyForecast> forecasts);
    void showDailyForecasts(@NonNull List<DailyForecast> forecasts);
    @StateStrategyType(OneExecutionStateStrategy.class)
    void removeYourself();
    @StateStrategyType(SkipStrategy.class)
    void showInfoMessage(String message);
}
