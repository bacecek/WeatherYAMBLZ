package com.zino.mobilization.weatheryamblz.settings;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.data.settings.units.PressureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.TemperatureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.Units;
import com.zino.mobilization.weatheryamblz.data.settings.units.WindSpeedUnit;

import org.junit.Test;

import io.reactivex.observers.TestObserver;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

public class ChangingValuesTest extends BaseSettingsTest {
    private final Units defaultUnits = new Units(
            TemperatureUnit.CELSIUS,
            PressureUnit.HPA,
            WindSpeedUnit.MS
    );
    private TestObserver<Units> unitsTestObserver;
    private TestObserver<Long> updateTestObserver;
    private TestObserver<Integer> radioTestObserver;

    @Override
    public void init() {
        super.init();
        unitsTestObserver = settingsManager.getUnits().test();
        updateTestObserver = settingsManager.getUpdateTime().test();
        radioTestObserver = settingsManager.getTimeRadioButtonId().test();
    }

    @Test
    public void shouldChangeTemperatureUnit() {
        unitsTestObserver.assertValue(defaultUnits);

        settingsManager.setTemperatureUnit(TemperatureUnit.FAHRENHEIT);
        Units newUnits = new Units(TemperatureUnit.FAHRENHEIT, defaultUnits.getPressureUnit(), defaultUnits.getWindSpeedUnit());

        unitsTestObserver.assertValues(defaultUnits, newUnits);
    }

    @Test
    public void shouldChangePressureUnit() {
        unitsTestObserver.assertValue(defaultUnits);

        settingsManager.setPressureUnit(PressureUnit.MMHG);
        Units newUnits = new Units(defaultUnits.getTemperatureUnit(), PressureUnit.MMHG, defaultUnits.getWindSpeedUnit());

        unitsTestObserver.assertValues(defaultUnits, newUnits);
    }

    @Test
    public void shouldChangeWindSpeedUnit() {
        unitsTestObserver.assertValue(defaultUnits);

        settingsManager.setWindSpeedUnit(WindSpeedUnit.KMH);
        Units newUnits = new Units(defaultUnits.getTemperatureUnit(), defaultUnits.getPressureUnit(), WindSpeedUnit.KMH);

        unitsTestObserver.assertValues(defaultUnits, newUnits);
    }

    @Test
    public void shouldChangeUpdateTime() {
        updateTestObserver.assertValue(900_000L);

        settingsManager.setUpdateTime(0);

        updateTestObserver.assertValues(900_000L, 0L);
    }

    @Test
    public void shouldChangeRadioButton() {
        radioTestObserver.assertValue(R.id.radio_fifteen);

        settingsManager.setTimeRadioButtonId(R.id.radio_thirteen);

        radioTestObserver.assertValues(R.id.radio_fifteen, R.id.radio_thirteen);
    }

}
