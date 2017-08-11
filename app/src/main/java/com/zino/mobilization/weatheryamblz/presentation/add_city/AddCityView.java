package com.zino.mobilization.weatheryamblz.presentation.add_city;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.zino.mobilization.weatheryamblz.business.entity.Suggestion;

import java.util.List;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

@StateStrategyType(SingleStateStrategy.class)
public interface AddCityView extends MvpView {
    void showSuggestions(List<Suggestion> suggestions);
    void showMessage(String message);
    void showLoading();
    void dismissWithSuccess();
    void dismissWithError();
}
