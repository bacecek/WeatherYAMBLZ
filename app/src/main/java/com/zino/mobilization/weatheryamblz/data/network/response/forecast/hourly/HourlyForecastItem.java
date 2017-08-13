package com.zino.mobilization.weatheryamblz.data.network.response.forecast.hourly;

import com.google.gson.annotations.SerializedName;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Main;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Weather;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Wind;

import java.util.List;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

public class HourlyForecastItem {
    @SerializedName("dt")
    private long time;
    @SerializedName("weather")
    private List<Weather> weatherInfo;
    @SerializedName("main")
    private Main mainInfo;
    @SerializedName("ic_wind")
    private Wind windInfo;

    public long getTime() {
        return time;
    }

    public List<Weather> getWeatherInfo() {
        return weatherInfo;
    }

    public Main getMainInfo() {
        return mainInfo;
    }

    public Wind getWindInfo() {
        return windInfo;
    }
}
