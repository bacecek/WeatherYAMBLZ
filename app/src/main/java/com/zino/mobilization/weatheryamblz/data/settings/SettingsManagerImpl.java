package com.zino.mobilization.weatheryamblz.data.settings;

import com.f2prateek.rx.preferences2.Preference;
import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.data.settings.units.PressureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.TemperatureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.Units;
import com.zino.mobilization.weatheryamblz.data.settings.units.WindSpeedUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Denis Buzmakov on 16.07.2017.
 * <buzmakov.da@gmail.com>
 */

public class SettingsManagerImpl implements SettingsManager {
    private static final String TEMPERATURE_UNIT = "unit_temperature";
    private static final String PRESSURE_UNIT = "unit_pressure";
    private static final String WIND_SPEED_UNIT = "unit_wind_speed";
    private static final String UPDATE_TIME_KEY = "update_time_key";
    private static final String SELECTED_RADIO_KEY = "selected_radio_button_key";

    private RxSharedPreferences rxSharedPreferences;

    @Inject
    public SettingsManagerImpl(RxSharedPreferences rxSharedPreferences) {
        this.rxSharedPreferences = rxSharedPreferences;
    }

    @Override
    public void setUpdateTime(long periodInMillis) {
        getUpdateTimePreference().set(periodInMillis);
    }

    @Override
    public Observable<Long> getUpdateTime() {
        return getUpdateTimePreference()
                .asObservable();
    }

    @Override
    public void setTimeRadioButtonId(int id) {
        getTimeRadioButtonIdPreference().set(id);
    }

    @Override
    public Observable<Integer> getTimeRadioButtonId() {
        return getTimeRadioButtonIdPreference().asObservable();
    }

    @Override
    public Observable<Units> getUnits() {
        Observable<TemperatureUnit> temperature = getTemperaturePreference().asObservable();
        Observable<PressureUnit> pressure = getPressurePreference().asObservable();
        Observable<WindSpeedUnit> wind = getWindSpeedPreference().asObservable();
        return Observable.combineLatest(temperature, pressure, wind, (Units::new))
                .distinctUntilChanged();
    }

    @Override
    public void setTemperatureUnit(TemperatureUnit unit) {
        getTemperaturePreference().set(unit);
    }

    @Override
    public void setPressureUnit(PressureUnit unit) {
        getPressurePreference().set(unit);
    }

    @Override
    public void setWindSpeedUnit(WindSpeedUnit unit) {
        getWindSpeedPreference().set(unit);
    }

    private Preference<Integer> getTimeRadioButtonIdPreference() {
        return rxSharedPreferences.getInteger(SELECTED_RADIO_KEY, R.id.radio_fifteen);
    }

    private Preference<Long> getUpdateTimePreference() {
        return rxSharedPreferences.getLong(UPDATE_TIME_KEY, 900_000L);
    }

    private Preference<TemperatureUnit> getTemperaturePreference() {
        return rxSharedPreferences.getEnum(
                TEMPERATURE_UNIT,
                TemperatureUnit.CELSIUS,
                TemperatureUnit.class);
    }

    private Preference<PressureUnit> getPressurePreference() {
        return rxSharedPreferences.getEnum(
                PRESSURE_UNIT,
                PressureUnit.HPA,
                PressureUnit.class);
    }

    private Preference<WindSpeedUnit> getWindSpeedPreference() {
        return rxSharedPreferences.getEnum(
                WIND_SPEED_UNIT,
                WindSpeedUnit.MS,
                WindSpeedUnit.class);
    }

}
