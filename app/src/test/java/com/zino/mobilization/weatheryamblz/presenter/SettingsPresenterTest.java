package com.zino.mobilization.weatheryamblz.presenter;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.data.settings.SettingsManager;
import com.zino.mobilization.weatheryamblz.data.settings.units.PressureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.TemperatureUnit;
import com.zino.mobilization.weatheryamblz.data.settings.units.WindSpeedUnit;
import com.zino.mobilization.weatheryamblz.presentation.settings.SettingsPresenter;
import com.zino.mobilization.weatheryamblz.presentation.settings.SettingsView$$State;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

public class SettingsPresenterTest {

    @Mock
    SettingsManager settingsManager;
    @Mock
    SettingsView$$State viewState;

    private SettingsPresenter settingsPresenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        settingsPresenter = new SettingsPresenter(settingsManager);
        settingsPresenter.setViewState(viewState);
    }

    @Test
    public void shouldSetCelsius() {
        settingsPresenter.onCelsiusChosen();

        verify(settingsManager, only()).setTemperatureUnit(TemperatureUnit.CELSIUS);
    }

    @Test
    public void shouldSetFahrenheit() {
        settingsPresenter.onFahrenheitChosen();

        verify(settingsManager, only()).setTemperatureUnit(TemperatureUnit.FAHRENHEIT);
    }

    @Test
    public void shouldSetMs() {
        settingsPresenter.onMsChosen();

        verify(settingsManager, only()).setWindSpeedUnit(WindSpeedUnit.MS);
    }

    @Test
    public void shouldSetKmh() {
        settingsPresenter.onKmhChosen();

        verify(settingsManager).setWindSpeedUnit(WindSpeedUnit.KMH);
    }

    @Test
    public void shouldSetMmhg() {
        settingsPresenter.onMmhgChosen();

        verify(settingsManager, only()).setPressureUnit(PressureUnit.MMHG);
    }

    @Test
    public void shouldSetHpa() {
        settingsPresenter.onHpaChosen();

        verify(settingsManager, only()).setPressureUnit(PressureUnit.HPA);
    }

    @Test
    public void shouldChangeUpdateTime() {
        settingsPresenter.onTimeCheckedChanged(R.id.radio_thirteen);

        verify(settingsManager).setTimeRadioButtonId(R.id.radio_thirteen);
        verify(settingsManager).setUpdateTime(1_800_000L);
    }
}
