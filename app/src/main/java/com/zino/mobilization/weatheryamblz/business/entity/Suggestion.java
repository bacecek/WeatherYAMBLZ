package com.zino.mobilization.weatheryamblz.business.entity;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public class Suggestion {
    private String cityId;
    private String description;

    public Suggestion(String cityId, String description) {
        this.cityId = cityId;
        this.description = description;
    }

    public String getCityId() {
        return cityId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "cityId='" + cityId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Suggestion)) return false;

        Suggestion that = (Suggestion) o;

        if (cityId != null ? !cityId.equals(that.cityId) : that.cityId != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = cityId != null ? cityId.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
