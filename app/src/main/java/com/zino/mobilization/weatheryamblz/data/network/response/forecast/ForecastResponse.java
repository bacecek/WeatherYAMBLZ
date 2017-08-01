package com.zino.mobilization.weatheryamblz.data.network.response.forecast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

public class ForecastResponse {
    @SerializedName("city")
    private CityInfo cityInfo;
    @SerializedName("cnt")
    private int corefastsCount;
    @SerializedName("list")
    private List<Forecast> forecasts;
}
