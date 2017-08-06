package com.zino.mobilization.weatheryamblz.business.entity;

/**
 * Created by Denis Buzmakov on 05.08.17.
 * <buzmakov.da@gmail.com>
 */

public class CurrentWeather {
    private String temperature;

    private String description;

    private String humidity;

    private String pressure;

    private String sunriseTime;

    private String sunsetTime;

    private String windSpeed;

    private String visibility;

    private String cloudiness;

    private int conditionId;

    private String iconId;

    public CurrentWeather(String temperature,
                          String description,
                          String humidity,
                          String pressure,
                          String sunriseTime,
                          String sunsetTime,
                          String windSpeed,
                          String visibility,
                          String cloudiness,
                          int conditionId,
                          String iconId) {
        this.temperature = temperature;
        this.description = description;
        this.humidity = humidity;
        this.pressure = pressure;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.cloudiness = cloudiness;
        this.conditionId = conditionId;
        this.iconId = iconId;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public int getConditionId() {
        return conditionId;
    }

    public String getIconId() {
        return iconId;
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "temperature='" + temperature + '\'' +
                ", description='" + description + '\'' +
                ", humidity='" + humidity + '\'' +
                ", pressure='" + pressure + '\'' +
                ", sunriseTime='" + sunriseTime + '\'' +
                ", sunsetTime='" + sunsetTime + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", visibility='" + visibility + '\'' +
                ", cloudiness='" + cloudiness + '\'' +
                ", conditionId=" + conditionId +
                ", iconId='" + iconId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrentWeather)) return false;

        CurrentWeather that = (CurrentWeather) o;

        if (conditionId != that.conditionId) return false;
        if (!temperature.equals(that.temperature)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (humidity != null ? !humidity.equals(that.humidity) : that.humidity != null)
            return false;
        if (pressure != null ? !pressure.equals(that.pressure) : that.pressure != null)
            return false;
        if (sunriseTime != null ? !sunriseTime.equals(that.sunriseTime) : that.sunriseTime != null)
            return false;
        if (sunsetTime != null ? !sunsetTime.equals(that.sunsetTime) : that.sunsetTime != null)
            return false;
        if (windSpeed != null ? !windSpeed.equals(that.windSpeed) : that.windSpeed != null)
            return false;
        if (visibility != null ? !visibility.equals(that.visibility) : that.visibility != null)
            return false;
        if (cloudiness != null ? !cloudiness.equals(that.cloudiness) : that.cloudiness != null)
            return false;
        return iconId.equals(that.iconId);
    }

    @Override
    public int hashCode() {
        int result = temperature.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        result = 31 * result + (sunriseTime != null ? sunriseTime.hashCode() : 0);
        result = 31 * result + (sunsetTime != null ? sunsetTime.hashCode() : 0);
        result = 31 * result + (windSpeed != null ? windSpeed.hashCode() : 0);
        result = 31 * result + (visibility != null ? visibility.hashCode() : 0);
        result = 31 * result + (cloudiness != null ? cloudiness.hashCode() : 0);
        result = 31 * result + conditionId;
        result = 31 * result + iconId.hashCode();
        return result;
    }
}
