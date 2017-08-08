package com.zino.mobilization.weatheryamblz.data.network.response.forecast.daily;

import com.google.gson.annotations.SerializedName;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Weather;

import java.util.List;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

public class DailyForecastItem {
    @SerializedName("dt")
    private long time;
    @SerializedName("weather")
    private List<Weather> weatherInfo;
    @SerializedName("temp")
    private TempInfo tempInfo;
    private double pressure;
    private int humidity;

    public long getTime() {
        return time;
    }

    public List<Weather> getWeatherInfo() {
        return weatherInfo;
    }

    public TempInfo getTempInfo() {
        return tempInfo;
    }

    public double getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }
}
