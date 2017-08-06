package com.zino.mobilization.weatheryamblz.data.settings;

import io.reactivex.Observable;

/**
 * Created by Denis Buzmakov on 27.07.17.
 * <buzmakov.da@gmail.com>
 */

public interface SettingsManager {
    Observable<Boolean> isCelsius();
    void setCelsius(boolean isCelsuis);
    void setUpdateTime(long periodInMillis);
    Observable<Long> getUpdateTime();
    void setTimeRadioButtonId(int id);
    Observable<Integer> getTimeRadioButtonId();
}
