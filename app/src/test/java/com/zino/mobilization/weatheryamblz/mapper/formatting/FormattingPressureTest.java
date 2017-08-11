package com.zino.mobilization.weatheryamblz.mapper.formatting;

import com.zino.mobilization.weatheryamblz.data.settings.units.PressureUnit;
import com.zino.mobilization.weatheryamblz.mapper.BaseMapperTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Denis Buzmakov on 11.08.17.
 * <buzmakov.da@gmail.com>
 */

public class FormattingPressureTest extends BaseMapperTest {

    @Test
    public void shouldReturnPressureInMmhg() {
        assertEquals(mapper.formatPressure(1000, PressureUnit.MMHG), "750 mmHg");
        assertEquals(mapper.formatPressure(0, PressureUnit.MMHG), "0 mmHg");
        assertEquals(mapper.formatPressure(1001, PressureUnit.MMHG), "751 mmHg");
    }

    @Test
    public void shouldReturnPressureInHpa() {
        assertEquals(mapper.formatPressure(1000, PressureUnit.HPA), "1000 hPa");
        assertEquals(mapper.formatPressure(1000.56, PressureUnit.HPA), "1001 hPa");
    }

}
