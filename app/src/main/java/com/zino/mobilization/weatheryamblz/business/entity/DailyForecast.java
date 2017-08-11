package com.zino.mobilization.weatheryamblz.business.entity;

/**
 * Created by Denis Buzmakov on 06.08.17.
 * <buzmakov.da@gmail.com>
 */

public class DailyForecast {
    private String tempDay;

    private String tempNight;

    private String description;

    private String day;

    private int conditionId;

    private String iconId;

    public DailyForecast(String tempDay,
                         String tempNight,
                         String description,
                         String day,
                         int conditionId,
                         String iconId) {
        this.tempDay = tempDay;
        this.tempNight = tempNight;
        this.description = description;
        this.day = day;
        this.conditionId = conditionId;
        this.iconId = iconId;
    }

    public String getTempDay() {
        return tempDay;
    }

    public String getTempNight() {
        return tempNight;
    }

    public String getDescription() {
        return description;
    }

    public String getDay() {
        return day;
    }

    public int getConditionId() {
        return conditionId;
    }

    public String getIconId() {
        return iconId;
    }

    @Override
    public String toString() {
        return "DailyForecast{" +
                "tempDay='" + tempDay + '\'' +
                ", tempNight='" + tempNight + '\'' +
                ", description='" + description + '\'' +
                ", day='" + day + '\'' +
                ", conditionId=" + conditionId +
                ", iconId='" + iconId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DailyForecast)) return false;

        DailyForecast that = (DailyForecast) o;

        if (conditionId != that.conditionId) return false;
        if (tempDay != null ? !tempDay.equals(that.tempDay) : that.tempDay != null) return false;
        if (tempNight != null ? !tempNight.equals(that.tempNight) : that.tempNight != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        return iconId != null ? iconId.equals(that.iconId) : that.iconId == null;
    }

    @Override
    public int hashCode() {
        int result = tempDay != null ? tempDay.hashCode() : 0;
        result = 31 * result + (tempNight != null ? tempNight.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + conditionId;
        result = 31 * result + (iconId != null ? iconId.hashCode() : 0);
        return result;
    }
}
