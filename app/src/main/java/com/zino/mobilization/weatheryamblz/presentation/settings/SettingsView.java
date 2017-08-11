package com.zino.mobilization.weatheryamblz.presentation.settings;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SettingsView extends MvpView {
    void setFahrenheitButtonActive();
    void setCelsiusButtonActive();
    void setHpaButtonActive();
    void setMmhgButtonActive();
    void setMsButtonActive();
    void setKmhButtonActive();
    void checkRadioButton(int id);
}
