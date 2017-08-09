package com.zino.mobilization.weatheryamblz.business.entity;

/**
 * Created by Denis Buzmakov on 03.08.17.
 * <buzmakov.da@gmail.com>
 */

public class City {
    private String id;

    private String name;

    private String address;

    private double latitude;

    private double longitude;

    private CurrentWeather currentWeather;

    public City(String id, String name, String address, double latitude, double longitude, CurrentWeather currentWeather) {
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

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", currentWeather=" + currentWeather +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        return id.equals(city.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
