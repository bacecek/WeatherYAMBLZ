package com.zino.mobilization.weatheryamblz.data.db.entity;

/**
 * Created by Denis Buzmakov on 01.08.17.
 * <buzmakov.da@gmail.com>
 */


public class WeatherEntity {

    private double temperature;

    private String description;

    private double humidity;

    private double pressure;

    private long sunriseTime;

    private long sunsetTime;

    private double windSpeed;

    private int visibility;

    private int cloudiness;

    private int conditionId;

    private String iconId;

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

    public long getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(long sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public long getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(long sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(int cloudiness) {
        this.cloudiness = cloudiness;
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
        return "WeatherEntity{" +
                "temperature=" + temperature +
                ", description='" + description + '\'' +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", sunriseTime=" + sunriseTime +
                ", sunsetTime=" + sunsetTime +
                ", windSpeed=" + windSpeed +
                ", visibility=" + visibility +
                ", cloudiness=" + cloudiness +
                ", conditionId=" + conditionId +
                ", iconId='" + iconId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherEntity)) return false;

        WeatherEntity that = (WeatherEntity) o;

        if (Double.compare(that.temperature, temperature) != 0) return false;
        if (Double.compare(that.humidity, humidity) != 0) return false;
        if (Double.compare(that.pressure, pressure) != 0) return false;
        if (sunriseTime != that.sunriseTime) return false;
        if (sunsetTime != that.sunsetTime) return false;
        if (Double.compare(that.windSpeed, windSpeed) != 0) return false;
        if (visibility != that.visibility) return false;
        if (cloudiness != that.cloudiness) return false;
        if (conditionId != that.conditionId) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        return iconId != null ? iconId.equals(that.iconId) : that.iconId == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(temperature);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(humidity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pressure);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (sunriseTime ^ (sunriseTime >>> 32));
        result = 31 * result + (int) (sunsetTime ^ (sunsetTime >>> 32));
        temp = Double.doubleToLongBits(windSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + visibility;
        result = 31 * result + cloudiness;
        result = 31 * result + conditionId;
        result = 31 * result + iconId.hashCode();
        return result;
    }
}
