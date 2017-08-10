package com.zino.mobilization.weatheryamblz.data.network.response.places;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public class PlaceResponse {

    @SerializedName("result")
    private PlaceInfo placeInfo;

    public PlaceInfo getPlaceInfo() {
        return placeInfo;
    }

    @Override
    public String toString() {
        return "PlaceResponse{" +
                "placeInfo=" + placeInfo +
                '}';
    }
}
