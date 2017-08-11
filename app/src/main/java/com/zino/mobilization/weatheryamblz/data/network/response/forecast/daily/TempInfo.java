package com.zino.mobilization.weatheryamblz.data.network.response.forecast.daily;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis Buzmakov on 06.08.17.
 * <buzmakov.da@gmail.com>
 */

public class TempInfo {

    @SerializedName("day")
    private double tempDay;

    @SerializedName("min")
    private double tempMin;

    @SerializedName("max")
    private double tempMax;

    @SerializedName("night")
    private double tempNight;

    @SerializedName("eve")
    private double tempEvening;

    @SerializedName("morn")
    private double tempMorning;

    public double getTempDay() {
        return tempDay;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getTempNight() {
        return tempNight;
    }

    public double getTempEvening() {
        return tempEvening;
    }

    public double getTempMorning() {
        return tempMorning;
    }
}
