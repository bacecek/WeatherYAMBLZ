package com.zino.mobilization.weatheryamblz.data.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Denis Buzmakov on 06.08.17.
 * <buzmakov.da@gmail.com>
 */

@Entity(tableName = "daily_forecasts",
        indices = {@Index("cityId"), @Index("forecastId")},
        foreignKeys = {
                @ForeignKey(entity = CityEntity.class,
                        parentColumns = "id",
                        childColumns = "cityId",
                        onDelete = ForeignKey.CASCADE)
        })
public class DailyForecastEntity {
    @PrimaryKey(autoGenerate = true)
    private long forecastId;

    private String cityId;

    private long date;

    private double tempDay;

    private double tempNight;

    private String description;

    private double humidity;

    private double pressure;

    private int conditionId;

    private String iconId;

    public long getForecastId() {
        return forecastId;
    }

    public void setForecastId(long forecastId) {
        this.forecastId = forecastId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getTempDay() {
        return tempDay;
    }

    public void setTempDay(double tempDay) {
        this.tempDay = tempDay;
    }

    public double getTempNight() {
        return tempNight;
    }

    public void setTempNight(double tempNight) {
        this.tempNight = tempNight;
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
        return "DailyForecastEntity{" +
                "forecastId=" + forecastId +
                ", cityId='" + cityId + '\'' +
                ", date=" + date +
                ", tempDay=" + tempDay +
                ", tempNight=" + tempNight +
                ", description='" + description + '\'' +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", conditionId=" + conditionId +
                ", iconId='" + iconId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DailyForecastEntity)) return false;

        DailyForecastEntity that = (DailyForecastEntity) o;

        if (forecastId != that.forecastId) return false;
        return cityId != null ? cityId.equals(that.cityId) : that.cityId == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (forecastId ^ (forecastId >>> 32));
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        return result;
    }
}
