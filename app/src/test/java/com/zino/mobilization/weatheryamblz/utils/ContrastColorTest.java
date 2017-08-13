package com.zino.mobilization.weatheryamblz.utils;

import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.TestApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * Created by Denis Buzmakov on 12.08.17.
 * <buzmakov.da@gmail.com>
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApplication.class)
public class ContrastColorTest {

    @Test
    public void shouldReturnBlackColor() {
        assertEquals(0xFF000000, Utils.getContrastColor(0xFFFFFFFF));
        assertEquals(0xFF000000, Utils.getContrastColor(0xFFB2EBF2));
        assertEquals(0xFF000000, Utils.getContrastColor(0xFF9FC3F7));
        assertEquals(0xFF000000, Utils.getContrastColor(0xFFFFCC80));
    }

    @Test
    public void shouldReturnWhiteColor() {
        assertEquals(0xFFFFFFFF, Utils.getContrastColor(0xFFAD1457));
        assertEquals(0xFFFFFFFF, Utils.getContrastColor(0xFF4E342E));
        assertEquals(0xFFFFFFFF, Utils.getContrastColor(0xFF37474F));
        assertEquals(0xFFFFFFFF, Utils.getContrastColor(0xFF000000));
    }

}
