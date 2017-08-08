package com.zino.mobilization.weatheryamblz.data.network.response.common;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CityInfo {
    @SerializedName("id")
    private int cityId;
    private String name;
    @SerializedName("coord")
    private Coord coordinates;

    public int getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public Coord getCoordinates() {
        return coordinates;
    }
}
