package com.zino.mobilization.weatheryamblz.mapper.formatting;

import com.zino.mobilization.weatheryamblz.data.settings.units.TemperatureUnit;
import com.zino.mobilization.weatheryamblz.mapper.BaseMapperTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Denis Buzmakov on 11.08.17.
 * <buzmakov.da@gmail.com>
 */

public class FormattingTemperatureTest extends BaseMapperTest {

    @Test
    public void shouldReturnTemperatureInCelsius() {
        assertEquals(mapper.formatTemperature(273, TemperatureUnit.CELSIUS), "0 °");
        assertEquals(mapper.formatTemperature(270, TemperatureUnit.CELSIUS), "-3 °");
        assertEquals(mapper.formatTemperature(283, TemperatureUnit.CELSIUS), "10 °");
    }

    @Test
    public void shouldReturnTemperatureInFahrenheit() {
        assertEquals(mapper.formatTemperature(273, TemperatureUnit.FAHRENHEIT), "32 °");
        assertEquals(mapper.formatTemperature(270, TemperatureUnit.FAHRENHEIT), "26 °");
        assertEquals(mapper.formatTemperature(283, TemperatureUnit.FAHRENHEIT), "50 °");
    }

}
