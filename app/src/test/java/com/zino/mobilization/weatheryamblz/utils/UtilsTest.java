package com.zino.mobilization.weatheryamblz.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Denis Buzmakov on 12.08.17.
 * <buzmakov.da@gmail.com>
 */

public class UtilsTest {

    @Test
    public void shouldCapitalizeString() {
        assertEquals("Lol", Utils.capitalize("lol"));
        assertEquals("Lol", Utils.capitalize("Lol"));
        assertEquals("L", Utils.capitalize("l"));
        assertNull(Utils.capitalize(null));
    }

    @Test
    public void shouldFormatUnitTimeCorrect() {
        assertEquals("Сб, 12 августа", Utils.formatUnixTime(1502556408, "E, d MMMM"));
        assertEquals(" ", Utils.formatUnixTime(1502556408, " "));
        assertEquals("8:00", Utils.formatUnixTime(1501736408, "H:mm"));
    }

    @Test
    public void shouldConvertMsToKmh() {
        assertEquals(0d, Utils.convertMsToKmh(0), 0);
        assertEquals(36d, Utils.convertMsToKmh(10), 0);
        assertEquals(-36d, Utils.convertMsToKmh(-10), 0);
    }

    @Test
    public void shouldConvertHpaToMmhg() {
        assertEquals(750d, Utils.convertHpaToMmhg(1000), 1);
        assertEquals(787d, Utils.convertHpaToMmhg(1050), 1);
        assertEquals(0d, Utils.convertHpaToMmhg(0), 0);
    }

    @Test
    public void shouldConvertKelvinToCelsuis() {
        assertEquals(0, Utils.convertKelvinToCelsius(273), 1);
        assertEquals(10d, Math.round(Utils.convertKelvinToCelsius(283)), 1);
        assertEquals(-273d, Math.round(Utils.convertKelvinToCelsius(0)), 1);
    }

    @Test
    public void shouldConvertKelvinToFahrenheit() {
        assertEquals(50d, Utils.convertKelvinToFahrenheit(283), 1);
        assertEquals(14d, Math.round(Utils.convertKelvinToFahrenheit(263)), 1);
        assertEquals(91d, Math.round(Utils.convertKelvinToFahrenheit(306)), 1);
    }

    @Test
    public void shouldConvertMetersToKm() {
        assertEquals(5, Utils.metersToKm(5200));
        assertEquals(0, Utils.metersToKm(300));
        assertEquals(-3, Utils.metersToKm(-2598));
    }
}
