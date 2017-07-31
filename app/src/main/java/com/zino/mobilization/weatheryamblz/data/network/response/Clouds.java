package com.zino.mobilization.weatheryamblz.data.network.response;

import com.google.gson.annotations.SerializedName;


public class Clouds {
    @SerializedName("all")
    private int all;

    public int getAll() {
        return all;
    }

}
