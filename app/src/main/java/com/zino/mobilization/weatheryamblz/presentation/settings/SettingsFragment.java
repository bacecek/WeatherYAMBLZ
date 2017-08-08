package com.zino.mobilization.weatheryamblz.presentation.settings;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.presentation.common.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;


public class SettingsFragment extends BaseFragment implements SettingsView {
    @InjectPresenter
    SettingsPresenter presenter;

    @BindView(R.id.fahrenheit_button)
    Button fahrenheitButton;
    @BindView(R.id.celsius_button)
    Button celsiusButton;

    @BindView(R.id.time_radio_group)
    RadioGroup timeRadioGroup;

    @BindView(R.id.city_text_view)
    TextView cityTextView;

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

    @OnClick(R.id.celsius_button)
    void onCelsiusButtonClicked() {
        presenter.onCelsiusButtonClicked();
    }

    @OnClick(R.id.fahrenheit_button)
    void onFahrenheitButtonClicked() {
        presenter.onFahrenheitButtonClicked();
    }

    @Override
    public void setFahrenheitButtonActive() {
        celsiusButton.setEnabled(true);
        fahrenheitButton.setEnabled(false);
        fahrenheitButton.setTextColor(Color.WHITE);
        celsiusButton.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void setCelsiusButtonActive() {
        celsiusButton.setEnabled(false);
        fahrenheitButton.setEnabled(true);
        celsiusButton.setTextColor(Color.WHITE);
        fahrenheitButton.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void checkRadioButton(int id) {
        timeRadioGroup.check(id);
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }
}
