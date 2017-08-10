package com.zino.mobilization.weatheryamblz.presentation.settings;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zino.mobilization.weatheryamblz.utils.UnitsSwitch;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.presentation.common.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;


public class SettingsFragment extends BaseFragment implements SettingsView {
    @InjectPresenter
    SettingsPresenter presenter;

    @BindView(R.id.switch_temp) UnitsSwitch switchTemp;
    @BindView(R.id.switch_pressure) UnitsSwitch switchPressure;
    @BindView(R.id.switch_wind_speed) UnitsSwitch switchWindSpeed;
    @BindView(R.id.time_radio_group) RadioGroup timeRadioGroup;

    @ProvidePresenter
    SettingsPresenter provideSettingsPresenter() {
        return WeatherApplication.getAppComponent().getSettingsPresenter();
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timeRadioGroup.setOnCheckedChangeListener((radioGroup, id) -> presenter.onTimeCheckedChanged(id));
    }

    @OnClick(R.id.switch_temp)
    void onSwitchTemperatureUnit() {
        if(switchTemp.isLeftActive()) presenter.onFahrenheitChosen();
        else if(switchTemp.isRightActive()) presenter.onCelsiusChosen();
    }

    @OnClick(R.id.switch_pressure)
    void onSwitchPressureUnit() {
        if(switchPressure.isLeftActive()) presenter.onMmhgChosen();
        else if(switchPressure.isRightActive()) presenter.onHpaChosen();
    }

    @OnClick(R.id.switch_wind_speed)
    void onSwitchWindSpeedUnit() {
        if(switchWindSpeed.isLeftActive()) presenter.onKmhChosen();
        else if(switchWindSpeed.isRightActive()) presenter.onMsChosen();
    }

    @Override
    public void setFahrenheitButtonActive() {
        switchTemp.setActiveRight();
    }

    @Override
    public void setCelsiusButtonActive() {
        switchTemp.setActiveLeft();
    }

    @Override
    public void setHpaButtonActive() {
        switchPressure.setActiveLeft();
    }

    @Override
    public void setMmhgButtonActive() {
        switchPressure.setActiveRight();
    }

    @Override
    public void setMsButtonActive() {
        switchWindSpeed.setActiveLeft();
    }

    @Override
    public void setKmhButtonActive() {
        switchWindSpeed.setActiveRight();
    }

    @Override
    public void checkRadioButton(int id) {
        timeRadioGroup.check(id);
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }
}
