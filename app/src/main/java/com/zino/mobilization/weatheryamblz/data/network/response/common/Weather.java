package com.zino.mobilization.weatheryamblz.data.network.response.common;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis Buzmakov on 15.07.2017.
 * <buzmakov.da@gmail.com>
 */

public class Weather {

    @SerializedName("id")
    private int id;
    @SerializedName("main")
    private String shortDescription;
    @SerializedName("description")
    private String description;
    @SerializedName("icon")
    private String icon;

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }


}
