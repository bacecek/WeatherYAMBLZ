package com.zino.mobilization.weatheryamblz.presentation.settings;

import android.support.annotation.IdRes;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Алексей on 16.07.2017.
 */

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private SettingsManager settingsManager;

    public SettingsPresenter(SettingsManager settingsManager) {
        this.settingsManager = settingsManager;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        settingsManager.isCelsius()
                .subscribe(isCelsius -> {
                    if (isCelsius) {
                        getViewState().setCelsiusButtonActive();
                    } else {
                        getViewState().setFahrenheitButtonActive();
                    }
                });

        settingsManager.getTimeRadioButtonId()
                .subscribe(id -> {
                    if (id == 0) {
                        id = R.id.radio_fifteen;
                    }
                    getViewState().checkRadioButton(id);
                });
    }

    public void onCelsiusButtonClicked() {
        settingsManager.setCelsius(true);
    }

    public void onFahrenheitButtonClicked() {
        settingsManager.setCelsius(false);
    }

    public void onTimeCheckedChanged(@IdRes int id) {
        long updateTime = -1;
        switch (id) {
            case R.id.radio_manually:
                updateTime = 0;
                break;
            case R.id.radio_fifteen:
                updateTime = 900_000L;
                break;
            case R.id.radio_thirteen:
                updateTime = 1_800_000L;
                break;
            case R.id.radio_one_hour:
                updateTime = 3_600_000L;
                break;
            case R.id.radio_three_hour:
                updateTime = 10_800_000L;
                break;
        }
        if(updateTime == -1) return;

        settingsManager.setUpdateTime(updateTime);
        settingsManager.setTimeRadioButtonId(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
