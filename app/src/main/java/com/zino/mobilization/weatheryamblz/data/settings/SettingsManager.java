package com.zino.mobilization.weatheryamblz.data.settings;

import com.zino.mobilization.weatheryamblz.data.settings.units.PressureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.TemperatureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.Units;
import com.zino.mobilization.weatheryamblz.data.settings.units.WindSpeedUnit;

import io.reactivex.Observable;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

public interface SettingsManager {
    Observable<Units> getUnits();
    void setTemperatureUnit(TemperatureUnit unit);
    void setPressureUnit(PressureUnit unit);
    void setWindSpeedUnit(WindSpeedUnit unit);
    void setUpdateTime(long periodInMillis);
    Observable<Long> getUpdateTime();
    void setTimeRadioButtonId(int id);
    Observable<Integer> getTimeRadioButtonId();
}
