package com.zino.mobilization.weatheryamblz.data.network.response.places;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public class LocationInfo {
    @SerializedName("location")
    private CoordinatesInfo coordinatesInfo;

    public CoordinatesInfo getCoordinatesInfo() {
        return coordinatesInfo;
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
                "coordinatesInfo=" + coordinatesInfo +
                '}';
    }
}
