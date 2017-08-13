package com.zino.mobilization.weatheryamblz.mapper.formatting;

import com.zino.mobilization.weatheryamblz.data.settings.units.WindSpeedUnit;
import com.zino.mobilization.weatheryamblz.mapper.BaseMapperTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Denis Buzmakov on 11.08.17.
 * <buzmakov.da@gmail.com>
 */

public class FormattingWindSpeedTest extends BaseMapperTest {

    @Test
    public void shouldReturnWindSpeedInMs() {
        assertEquals(mapper.formatWindSpeed(1.1, WindSpeedUnit.MS), "1 m/s");
        assertEquals(mapper.formatWindSpeed(1.5, WindSpeedUnit.MS), "2 m/s");
    }

    @Test
    public void shouldReturnWindSpeedInKmh() {
        assertEquals(mapper.formatWindSpeed(1.1, WindSpeedUnit.KMH), "4 km/h");
        assertEquals(mapper.formatWindSpeed(5.4, WindSpeedUnit.KMH), "19 km/h");
        assertEquals(mapper.formatWindSpeed(100, WindSpeedUnit.KMH), "360 km/h");
    }
}
