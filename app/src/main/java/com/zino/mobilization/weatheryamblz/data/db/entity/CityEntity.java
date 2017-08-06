package com.zino.mobilization.weatheryamblz.data.db.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

@Entity(tableName = "cities")
public class CityEntity {

    @PrimaryKey
    private String id; //from Places API

    private String name;

    private String address;

    private double latitude;

    private double longitude;

    @Embedded
    private WeatherEntity currentWeather;

    public CityEntity(String id, String name, String address, double latitude, double longitude, WeatherEntity currentWeather) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.currentWeather = currentWeather;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public WeatherEntity getCurrentWeather() {
        return currentWeather;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityEntity)) return false;

        CityEntity city = (CityEntity) o;

        return id.equals(city.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
