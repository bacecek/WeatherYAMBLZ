package com.zino.mobilization.weatheryamblz.data.network.response.weather;

import com.google.gson.annotations.SerializedName;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Clouds;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Coord;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Main;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Rain;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Snow;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Weather;
import com.zino.mobilization.weatheryamblz.data.network.response.common.Wind;

import java.util.List;


public class WeatherResponse {

    @SerializedName("coord")
    private Coord coord;
    @SerializedName("weather")
    private List<Weather> weather;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private Main main;
    @SerializedName("visibility")
    private int visibility;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("dt")
    private long dt;
    @SerializedName("sys")
    private Sys sys;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private int cod;
    @SerializedName("snow")
    private Snow snowInfo;
    @SerializedName("rain")
    private Rain rainInfo;

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public int getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public long getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    public Snow getSnowInfo() {
        return snowInfo;
    }

    public Rain getRainInfo() {
        return rainInfo;
    }
}
