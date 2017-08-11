package com.zino.mobilization.weatheryamblz.presentation.weather;


import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.business.entity.City;
import com.zino.mobilization.weatheryamblz.business.entity.CurrentWeather;
import com.zino.mobilization.weatheryamblz.business.entity.DailyForecast;
import com.zino.mobilization.weatheryamblz.business.entity.HourlyForecast;
import com.zino.mobilization.weatheryamblz.presentation.common.BaseFragment;
import com.zino.mobilization.weatheryamblz.presentation.weather.adapter.DailyForecastsAdapter;
import com.zino.mobilization.weatheryamblz.presentation.weather.adapter.HourlyForecastsAdapter;
import com.zino.mobilization.weatheryamblz.utils.Utils;

import java.util.List;

import butterknife.BindView;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends BaseFragment implements WeatherView {
    private final static String KEY_CITY_ID = "city_id";

    @InjectPresenter
    WeatherPresenter presenter;

    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.layout_parent) LinearLayout layoutParent;
    @BindView(R.id.txt_city) TextView txtCity;
    @BindView(R.id.txt_description) TextView txtDescription;
    @BindView(R.id.txt_temperature) TextView txtTemp;
    @BindView(R.id.txt_humidity_value) TextView txtHumidity;
    @BindView(R.id.txt_wind_value) TextView txtWind;
    @BindView(R.id.txt_pressure_value) TextView txtPressure;
    @BindView(R.id.list_hourly_forecast) RecyclerView rvHourlyForecasts;
    @BindView(R.id.list_daily_forecast) RecyclerView rvDailyForecasts;
    @BindView(R.id.txt_empty_daily) TextView txtEmptyDaily;
    @BindView(R.id.txt_empty_hourly) TextView txtEmptyHourly;
    @BindView(R.id.txt_humidity_title) TextView txtHumidityTitle;
    @BindView(R.id.txt_wind_title) TextView txtWindTitle;
    @BindView(R.id.txt_pressure_title) TextView txtPressureTitle;
    @BindView(R.id.view_background) CardView viewBackgroundCurrentWeather;
    @BindView(R.id.img_condition) ImageView imgCondition;
    @BindView(R.id.img_humidity) ImageView imgHumidity;
    @BindView(R.id.img_wind) ImageView imgPressure;
    @BindView(R.id.img_pressure) ImageView imgWind;

    private DailyForecastsAdapter dailyForecastsAdapter;
    private HourlyForecastsAdapter hourlyForecastsAdapter;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String cityId = getArguments().getString(KEY_CITY_ID);
        presenter.setCityId(cityId);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout.setOnRefreshListener(() -> presenter.onRefresh());

        rvHourlyForecasts.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvHourlyForecasts.setHasFixedSize(true);
        rvHourlyForecasts.setNestedScrollingEnabled(false);
        RecyclerView.ItemAnimator dailyAnimator = rvHourlyForecasts.getItemAnimator();
        if(dailyAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) dailyAnimator).setSupportsChangeAnimations(false);
        }

        rvDailyForecasts.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDailyForecasts.setHasFixedSize(true);
        rvDailyForecasts.setNestedScrollingEnabled(false);
        RecyclerView.ItemAnimator hourlyAnimator = rvDailyForecasts.getItemAnimator();
        if(hourlyAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) hourlyAnimator).setSupportsChangeAnimations(false);
        }
    }

    @Override
    public void showCity(@NonNull City city) {
        Timber.d("showing city: " + city);
        txtCity.setText(city.getName());
        CurrentWeather currentWeather = city.getCurrentWeather();
        if (currentWeather != null) {
            txtDescription.setText(currentWeather.getDescription());
            txtTemp.setText(currentWeather.getTemperature());
            txtHumidity.setText(currentWeather.getHumidity());
            txtWind.setText(currentWeather.getWindSpeed());
            txtPressure.setText(currentWeather.getPressure());
        }
        applyColors(currentWeather);
    }

    @Override
    public void showHourlyForecasts(@NonNull List<HourlyForecast> forecasts) {
        Timber.d("showing hourly forecasts: " + forecasts);
        if(forecasts.size() > 0) {
            txtEmptyHourly.setVisibility(View.GONE);
            rvHourlyForecasts.setVisibility(View.VISIBLE);
            if (hourlyForecastsAdapter == null) {
                hourlyForecastsAdapter = new HourlyForecastsAdapter(forecasts);
                rvHourlyForecasts.setAdapter(hourlyForecastsAdapter);
            } else {
                hourlyForecastsAdapter.updateData(forecasts);
            }
        } else {
            txtEmptyHourly.setVisibility(View.VISIBLE);
            rvHourlyForecasts.setVisibility(View.GONE);
        }
    }

    @Override
    public void showDailyForecasts(@NonNull List<DailyForecast> forecasts) {
        Timber.d("showing daily forecasts: " + forecasts);
        if(forecasts.size() > 0) {
            txtEmptyDaily.setVisibility(View.GONE);
            rvDailyForecasts.setVisibility(View.VISIBLE);
            if (dailyForecastsAdapter == null) {
                dailyForecastsAdapter = new DailyForecastsAdapter(forecasts);
                rvDailyForecasts.setAdapter(dailyForecastsAdapter);
            } else {
                dailyForecastsAdapter.updateData(forecasts);
            }
        } else {
            txtEmptyDaily.setVisibility(View.VISIBLE);
            rvDailyForecasts.setVisibility(View.GONE);
        }
    }

    @Override
    public void setLoadingVisible(boolean visible) {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(visible));
    }

    private void applyColors(@Nullable CurrentWeather currentWeather) {
        boolean isDownloading = currentWeather == null;
        @ColorRes int backgroundColorRes;
        if(isDownloading) {
            backgroundColorRes = R.color.colorDownloadingWeatherBackground;
        } else {
            backgroundColorRes = Utils.getColorIdByWeatherCondition(currentWeather.getIconId());
        }
        int backgroundColor = ContextCompat.getColor(getActivity(), backgroundColorRes);
        viewBackgroundCurrentWeather.setCardBackgroundColor(backgroundColor);

        int textColor = Utils.getContrastColor(backgroundColor);

        if(!isDownloading) {
            @DrawableRes int conditionRes = Utils.getImageIdByWeatherCondition(currentWeather.getIconId());
            imgCondition.setImageResource(conditionRes);
            imgCondition.setColorFilter(textColor);
        }

        txtCity.setTextColor(textColor);
        txtDescription.setTextColor(textColor);
        txtTemp.setTextColor(textColor);
        txtWind.setTextColor(textColor);
        txtPressure.setTextColor(textColor);
        txtHumidity.setTextColor(textColor);
        imgHumidity.setColorFilter(textColor);
        imgWind.setColorFilter(textColor);
        imgPressure.setColorFilter(textColor);
        txtPressureTitle.setTextColor(textColor);
        txtWindTitle.setTextColor(textColor);
        txtHumidityTitle.setTextColor(textColor);
    }

    @Override
    public void removeYourself() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }

    @Override
    public void showInfoMessage(String message) {
        Snackbar.make(layoutParent, message, Snackbar.LENGTH_SHORT).show();
    }

    public static WeatherFragment newInstance(@NonNull String cityId) {
        Bundle args = new Bundle();
        args.putString(KEY_CITY_ID, cityId);

        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
