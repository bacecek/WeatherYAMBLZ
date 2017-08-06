package com.zino.mobilization.weatheryamblz.presentation.weather;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.data.network.response.weather.WeatherResponse;
import com.zino.mobilization.weatheryamblz.presentation.common.BaseFragment;
import com.zino.mobilization.weatheryamblz.utils.Utils;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends BaseFragment implements WeatherView {

    @InjectPresenter
    WeatherPresenter presenter;

    @BindView(R.id.weather_image_view)
    ImageView weatherImageView;

    @BindView(R.id.temp_text_view)
    TextView tempTextView;

    @BindView(R.id.min_temp_text_view)
    TextView minTempTextView;

    @BindView(R.id.max_temp_text_view)
    TextView maxTempTextView;

    @BindView(R.id.description_text_view)
    TextView descriptionTextView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.pressure_text_view)
    TextView pressureTextView;

    @BindView(R.id.humidity_text_view)
    TextView humidityTextView;

    @BindView(R.id.visibility_text_view)
    TextView visibilityTextView;

    @BindView(R.id.wind_text_view)
    TextView windSpeedTextView;

    @BindView(R.id.city_text_view)
    TextView cityTextView;

    private boolean isCelsius = true;

    @ProvidePresenter
    WeatherPresenter provideWeatherPresenter() {
        return WeatherApplication.getAppComponent().getWeatherPresenter();
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onNavigationChanged.setTitle(getResources().getString(R.string.action_weather));

        swipeRefreshLayout.setOnRefreshListener(() -> presenter.onRefresh());
    }

    @Override
    public void showWeather(WeatherResponse weatherResponse) {

        descriptionTextView.setText(weatherResponse.getWeather().get(0).getDescription());

        weatherImageView.setImageDrawable(
                getResources().getDrawable(
                        Utils.getImageIdByWeatherCondition(weatherResponse.getWeather().get(0).getIcon())));

        pressureTextView.setText(String.format(getResources().getString(R.string.pressure),
                String.valueOf(weatherResponse.getMain().getPressure())));

        humidityTextView.setText(String.format(getResources().getString(R.string.humidity),
                String.valueOf(weatherResponse.getMain().getHumidity())));

        visibilityTextView.setText(String.format(getResources().getString(R.string.visibility),
                String.valueOf(Utils.metersToKm(weatherResponse.getVisibility()))));

        windSpeedTextView.setText(String.format(getResources().getString(R.string.wind),
                String.valueOf(weatherResponse.getWind().getSpeed())));
    }

    @Override
    public void showCity(City city) {
        cityTextView.setText(city.getName());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setCelsius(boolean celsius) {
        isCelsius = celsius;
    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }
}
