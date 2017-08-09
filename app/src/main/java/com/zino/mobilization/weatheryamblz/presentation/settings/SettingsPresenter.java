package com.zino.mobilization.weatheryamblz.presentation.settings;

import android.support.annotation.IdRes;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.data.settings.units.PressureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.TemperatureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.Units;
import com.zino.mobilization.weatheryamblz.data.settings.units.WindSpeedUnit;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Created by Denis Buzmakov on 16.07.2017.
 * <buzmakov.da@gmail.com>
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

        settingsManager.getUnits()
                .subscribe(this::showUnits);

        settingsManager.getTimeRadioButtonId()
                .subscribe(id -> {
                    if (id == 0) {
                        id = R.id.radio_fifteen;
                    }
                    getViewState().checkRadioButton(id);
                });
    }

    private void showUnits(Units units) {
        switch (units.getTemperatureUnit()) {
            case CELSIUS: getViewState().setCelsiusButtonActive(); break;
            case FAHRENHEIT: getViewState().setFahrenheitButtonActive(); break;
        }
        switch (units.getPressureUnit()) {
            case HPA: getViewState().setHpaButtonActive(); break;
            case MMHG: getViewState().setMmhgButtonActive(); break;
        }
        switch (units.getWindSpeedUnit()) {
            case MS: getViewState().setMsButtonActive(); break;
            case KMH: getViewState().setKmhButtonActive(); break;
        }
    }

    public void onCelsiusChosen() {
        Timber.d("celsius chosen");
        settingsManager.setTemperatureUnit(TemperatureUnit.CELSIUS);
    }

    public void onFahrenheitChosen() {
        Timber.d("fahrenheit chosen");
        settingsManager.setTemperatureUnit(TemperatureUnit.FAHRENHEIT);
    }

    public void onHpaChosen() {
        Timber.d("hpa chosen");
        settingsManager.setPressureUnit(PressureUnit.HPA);
    }

    public void onMmhgChosen() {
        Timber.d("mmhg chosen");
        settingsManager.setPressureUnit(PressureUnit.MMHG);
    }

    public void onMsChosen() {
        Timber.d("ms chosen");
        settingsManager.setWindSpeedUnit(WindSpeedUnit.MS);
    }

    public void onKmhChosen() {
        Timber.d("kmh chosen");
        settingsManager.setWindSpeedUnit(WindSpeedUnit.KMH);
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
