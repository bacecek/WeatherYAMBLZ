package com.zino.mobilization.weatheryamblz.data.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */

@Entity(tableName = "hourly_forecasts",
        indices = {@Index("cityId"), @Index("forecastId")},
        foreignKeys = {
                @ForeignKey(entity = CityEntity.class,
                        parentColumns = "id",
                        childColumns = "cityId",
                        onDelete = ForeignKey.CASCADE)
        })
public class HourlyForecastEntity {
    @PrimaryKey(autoGenerate = true)
    private long forecastId;

    private String cityId;

    private long time;

    private double temperature;

    private String description;

    private double humidity;

    private double pressure;

    private double windSpeed;

    private int conditionId;

    private String iconId;

    public long getForecastId() {
        return forecastId;
    }

    public void setForecastId(long id) {
        this.forecastId = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getConditionId() {
        return conditionId;
    }

    public void setConditionId(int conditionId) {
        this.conditionId = conditionId;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    @Override
    public String toString() {
        return "HourlyForecastEntity{" +
                "forecastId=" + forecastId +
                ", cityId='" + cityId + '\'' +
                ", time=" + time +
                ", temperature=" + temperature +
                ", description='" + description + '\'' +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", windSpeed=" + windSpeed +
                ", conditionId=" + conditionId +
                ", iconId='" + iconId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HourlyForecastEntity)) return false;

        HourlyForecastEntity entity = (HourlyForecastEntity) o;

        if (forecastId != entity.forecastId) return false;
        return cityId != null ? cityId.equals(entity.cityId) : entity.cityId == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (forecastId ^ (forecastId >>> 32));
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        return result;
    }
}
