package com.zino.mobilization.weatheryamblz.data.db.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CityWithForecast {
    @Embedded
    private City city;

    @Relation(parentColumn = "id", entityColumn = "cityId")
    private List<Forecast> forecasts;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public String toString() {
        return "CityWithForecast{" +
                "city=" + city +
                ", forecasts=" + forecasts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityWithForecast)) return false;

        CityWithForecast that = (CityWithForecast) o;

        return city.equals(that.city) && (forecasts != null ? forecasts.equals(that.forecasts) : that.forecasts == null);
    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + (forecasts != null ? forecasts.hashCode() : 0);
        return result;
    }
}
