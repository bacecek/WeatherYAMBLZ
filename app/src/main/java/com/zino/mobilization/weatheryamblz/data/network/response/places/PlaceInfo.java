package com.zino.mobilization.weatheryamblz.data.network.response.places;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public class PlaceInfo {

    @SerializedName("place_id")
    private String id;

    private String name;

    @SerializedName("formatted_address")
    private String address;

    @SerializedName("geometry")
    private LocationInfo locationInfo;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    @Override
    public String toString() {
        return "PlaceInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", locationInfo=" + locationInfo +
                '}';
    }
}
