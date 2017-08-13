package com.zino.mobilization.weatheryamblz.settings;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.data.settings.units.PressureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.TemperatureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.Units;
import com.zino.mobilization.weatheryamblz.data.settings.units.WindSpeedUnit;

import org.junit.Test;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

public class DefaultValuesTest extends BaseSettingsTest {
    private final Units defaultUnits = new Units(
            TemperatureUnit.CELSIUS,
            PressureUnit.HPA,
            WindSpeedUnit.MS
    );

    @Test
    public void shouldReturnCelsius() {
        settingsManager.getUnits()
                .test()
                .assertValue(units -> units.getTemperatureUnit() == defaultUnits.getTemperatureUnit());
    }

    @Test
    public void shouldReturnHpa() {
        settingsManager.getUnits()
                .test()
                .assertValue(units -> units.getPressureUnit() == defaultUnits.getPressureUnit());
    }

    @Test
    public void shouldReturnMs() {
        settingsManager.getUnits()
                .test()
                .assertValue(units -> units.getWindSpeedUnit() == defaultUnits.getWindSpeedUnit());
    }

    @Test
    public void shouldReturnDefaultInterval() {
        settingsManager.getUpdateTime()
                .test()
                .assertValue(900_000L);
    }

    @Test
    public void shouldReturnDefaultRadioButton() {
        settingsManager.getTimeRadioButtonId()
                .test()
                .assertValue(R.id.radio_fifteen);
    }
}
