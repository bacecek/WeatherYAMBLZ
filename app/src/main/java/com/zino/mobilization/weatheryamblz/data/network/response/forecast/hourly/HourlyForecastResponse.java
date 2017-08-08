package com.zino.mobilization.weatheryamblz.data.network.response.forecast.hourly;

import com.google.gson.annotations.SerializedName;
import com.zino.mobilization.weatheryamblz.data.network.response.common.CityInfo;

import java.util.List;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

public class HourlyForecastResponse {
    @SerializedName("city")
    private CityInfo cityInfo;
    @SerializedName("cnt")
    private int corefastsCount;
    @SerializedName("list")
    private List<HourlyForecastItem> hourlyForecastItems;

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public int getCorefastsCount() {
        return corefastsCount;
    }

    public List<HourlyForecastItem> getHourlyForecastItems() {
        return hourlyForecastItems;
    }
}
