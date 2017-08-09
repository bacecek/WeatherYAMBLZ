package com.zino.mobilization.weatheryamblz.data.settings.units;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public class Units {
    private TemperatureUnit temperatureUnit;
    private PressureUnit pressureUnit;
    private WindSpeedUnit windSpeedUnit;

    public Units(TemperatureUnit temperatureUnit, PressureUnit pressureUnit, WindSpeedUnit windSpeedUnit) {
        this.temperatureUnit = temperatureUnit;
        this.pressureUnit = pressureUnit;
        this.windSpeedUnit = windSpeedUnit;
    }

    public TemperatureUnit getTemperatureUnit() {
        return temperatureUnit;
    }

    public PressureUnit getPressureUnit() {
        return pressureUnit;
    }

    public WindSpeedUnit getWindSpeedUnit() {
        return windSpeedUnit;
    }
}
