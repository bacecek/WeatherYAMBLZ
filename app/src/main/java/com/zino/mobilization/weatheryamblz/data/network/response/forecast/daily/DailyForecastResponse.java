package com.zino.mobilization.weatheryamblz.data.network.response.forecast.daily;

import com.google.gson.annotations.SerializedName;
import com.zino.mobilization.weatheryamblz.data.network.response.common.CityInfo;

import java.util.List;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

public class DailyForecastResponse {
    @SerializedName("city")
    private CityInfo cityInfo;
    @SerializedName("cnt")
    private int corefastsCount;
    @SerializedName("list")
    private List<DailyForecastItem> dailyForecastItems;

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public int getCorefastsCount() {
        return corefastsCount;
    }

    public List<DailyForecastItem> getDailyForecastItems() {
        return dailyForecastItems;
    }
}
