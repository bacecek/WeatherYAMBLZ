package com.zino.mobilization.weatheryamblz.utils;

import com.zino.mobilization.weatheryamblz.R;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Denis Buzmakov on 12.08.17.
 * <buzmakov.da@gmail.com>
 */

public class ColorConditionWeatherTest {

    @Test
    public void shouldReturnSunColor() {
        assertEquals(R.color.colorSun, Utils.getColorIdByWeatherCondition("01d"));
        assertEquals(R.color.colorSun, Utils.getColorIdByWeatherCondition("01n"));
    }

    @Test
    public void shouldReturnCloudColor() {
        assertEquals(R.color.colorClouds, Utils.getColorIdByWeatherCondition("03d"));
        assertEquals(R.color.colorClouds, Utils.getColorIdByWeatherCondition("03n"));
        assertEquals(R.color.colorClouds, Utils.getColorIdByWeatherCondition("04d"));
        assertEquals(R.color.colorClouds, Utils.getColorIdByWeatherCondition("04n"));
    }

    @Test
    public void shouldReturnCloudSunColor() {
        assertEquals(R.color.colorCloudsSun, Utils.getColorIdByWeatherCondition("02d"));
        assertEquals(R.color.colorCloudsSun, Utils.getColorIdByWeatherCondition("02n"));
    }

    @Test
    public void shouldReturnMistColor() {
        assertEquals(R.color.colorMist, Utils.getColorIdByWeatherCondition("50d"));
        assertEquals(R.color.colorMist, Utils.getColorIdByWeatherCondition("50d"));
    }

    @Test
    public void shouldReturnRainColor() {
        assertEquals(R.color.colorRain, Utils.getColorIdByWeatherCondition("10d"));
        assertEquals(R.color.colorRain, Utils.getColorIdByWeatherCondition("10n"));
        assertEquals(R.color.colorRain, Utils.getColorIdByWeatherCondition("09d"));
        assertEquals(R.color.colorRain, Utils.getColorIdByWeatherCondition("09n"));
    }

    @Test
    public void shouldReturnStormColor() {
        assertEquals(R.color.colorThunderstorm, Utils.getColorIdByWeatherCondition("11d"));
        assertEquals(R.color.colorThunderstorm, Utils.getColorIdByWeatherCondition("11n"));
    }

    @Test
    public void shouldReturnSnowColor() {
        assertEquals(R.color.colorSnow, Utils.getColorIdByWeatherCondition("13d"));
        assertEquals(R.color.colorSnow, Utils.getColorIdByWeatherCondition("13n"));
    }

    @Test
    public void shouldReturnNullColor() {
        assertEquals(0, Utils.getColorIdByWeatherCondition(null));
        assertEquals(0, Utils.getColorIdByWeatherCondition("azaza"));
    }
}
