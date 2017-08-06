package com.zino.mobilization.weatheryamblz.data.settings;

import android.content.SharedPreferences;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.zino.mobilization.weatheryamblz.utils.AppResources;

import io.reactivex.Observable;

/**
 * Created by Алексей on 16.07.2017.
 */

public class SettingsManagerImpl implements SettingsManager {

    public  static final String FILE_NAME = "app_preferences";
    private static final String IS_CELSIUS_KEY = "is_celsius_key";
    private static final String UPDATE_TIME_KEY = "update_time_key";
    private static final String SELECTED_RADIO_KEY = "selected_radio_button_key";
    private static final String CURRENT_CITY = "current_city";

    private SharedPreferences sharedPreferences;
    private RxSharedPreferences rxSharedPreferences;
    private AppResources appResources;

    public SettingsManagerImpl(
            SharedPreferences sharedPreferences,
            RxSharedPreferences rxSharedPreferences,
            AppResources resources) {
        this.sharedPreferences = sharedPreferences;
        this.rxSharedPreferences = rxSharedPreferences;
        this.appResources = resources;
    }

    @Override
    public Observable<Boolean> isCelsius() {
        return rxSharedPreferences.getBoolean(IS_CELSIUS_KEY, true)
                .asObservable();
    }

    @Override
    public void setCelsius(boolean isCelsuis) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_CELSIUS_KEY, isCelsuis);
        editor.apply();
    }

    @Override
    public void setUpdateTime(long periodInMillis) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(UPDATE_TIME_KEY, periodInMillis);
        editor.apply();
    }

    @Override
    public Observable<Long> getUpdateTime() {
        return rxSharedPreferences.getLong(UPDATE_TIME_KEY, 900_000L)
                .asObservable();
    }

    @Override
    public void setTimeRadioButtonId(int id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SELECTED_RADIO_KEY, id);
        editor.apply();
    }

    @Override
    public Observable<Integer> getTimeRadioButtonId() {
        return rxSharedPreferences.getInteger(SELECTED_RADIO_KEY, 0)
                .asObservable();
    }

}
