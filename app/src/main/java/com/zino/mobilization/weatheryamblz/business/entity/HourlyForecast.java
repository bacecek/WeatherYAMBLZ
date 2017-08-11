package com.zino.mobilization.weatheryamblz.business.entity;

/**
 * Created by Denis Buzmakov on 06.08.17.
 * <buzmakov.da@gmail.com>
 */

public class HourlyForecast {

    private String temperature;

    private String time;

    private int conditionId;

    private String iconId;

    public HourlyForecast(String temperature, String time, int conditionId, String iconId) {
        this.temperature = temperature;
        this.time = time;
        this.conditionId = conditionId;
        this.iconId = iconId;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getTime() {
        return time;
    }

    public int getConditionId() {
        return conditionId;
    }

    public String getIconId() {
        return iconId;
    }

    @Override
    public String toString() {
        return "HourlyForecastItem{" +
                "temperature='" + temperature + '\'' +
                ", time='" + time + '\'' +
                ", conditionId=" + conditionId +
                ", iconId='" + iconId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HourlyForecast)) return false;

        HourlyForecast forecast = (HourlyForecast) o;

        if (conditionId != forecast.conditionId) return false;
        if (temperature != null ? !temperature.equals(forecast.temperature) : forecast.temperature != null)
            return false;
        if (time != null ? !time.equals(forecast.time) : forecast.time != null) return false;
        return iconId != null ? iconId.equals(forecast.iconId) : forecast.iconId == null;
    }

    @Override
    public int hashCode() {
        int result = temperature != null ? temperature.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + conditionId;
        result = 31 * result + (iconId != null ? iconId.hashCode() : 0);
        return result;
    }
}
