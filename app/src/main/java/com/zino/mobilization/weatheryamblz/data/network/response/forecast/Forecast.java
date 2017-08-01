package com.zino.mobilization.weatheryamblz.data.network.response.forecast;

import com.google.gson.annotations.SerializedName;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Clouds;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Main;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Rain;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Snow;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Weather;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Wind;

import java.util.List;

/**
 * Created by Denis Buzmakov on 02.08.17.
 * <buzmakov.da@gmail.com>
 */

public class Forecast {
    @SerializedName("dt")
    private String time;
    @SerializedName("weather")
    private List<Weather> weatherInfo;
    @SerializedName("main")
    private Main mainInfo;
    @SerializedName("wind")
    private Wind windInfo;
    @SerializedName("clouds")
    private Clouds cloudsInfo;
    @SerializedName("snow")
    private Snow snowInfo;
    @SerializedName("rain")
    private Rain rainInfo;

    public String getTime() {
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

    public Clouds getCloudsInfo() {
        return cloudsInfo;
    }

    public Snow getSnowInfo() {
        return snowInfo;
    }

    public Rain getRainInfo() {
        return rainInfo;
    }
}
