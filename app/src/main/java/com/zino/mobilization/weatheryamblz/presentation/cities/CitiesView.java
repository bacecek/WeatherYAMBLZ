package com.zino.mobilization.weatheryamblz.presentation.cities;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.zino.mobilization.weatheryamblz.business.entity.City;

import java.util.List;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CitiesView extends MvpView {
    void setEmptyViewVisible(boolean visible);
    void setCitiesListVisible(boolean visible);
    void updateCities(List<City> cities);
    @StateStrategyType(OneExecutionStateStrategy.class)
    void openChooseCity();
}
