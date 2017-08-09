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

    @Override
    public String toString() {
        return "Units{" +
                "temperatureUnit=" + temperatureUnit +
                ", pressureUnit=" + pressureUnit +
                ", windSpeedUnit=" + windSpeedUnit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Units)) return false;

        Units units = (Units) o;

        if (temperatureUnit != units.temperatureUnit) return false;
        if (pressureUnit != units.pressureUnit) return false;
        return windSpeedUnit == units.windSpeedUnit;
    }

    @Override
    public int hashCode() {
        int result = temperatureUnit != null ? temperatureUnit.hashCode() : 0;
        result = 31 * result + (pressureUnit != null ? pressureUnit.hashCode() : 0);
        result = 31 * result + (windSpeedUnit != null ? windSpeedUnit.hashCode() : 0);
        return result;
    }
}
