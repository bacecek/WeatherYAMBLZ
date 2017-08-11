package com.zino.mobilization.weatheryamblz.data.network.response.common;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

public class Snow {
    @SerializedName("3h")
    private double volume;

    public double getVolume() {
        return volume;
    }
}
