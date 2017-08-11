package com.zino.mobilization.weatheryamblz.data.network.response.places;

import com.google.gson.annotations.SerializedName;

public class CoordinatesInfo {

    @SerializedName("lat")
    private float latitude;

    @SerializedName("lng")
    private float longitude;

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "CoordinatesInfo{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
