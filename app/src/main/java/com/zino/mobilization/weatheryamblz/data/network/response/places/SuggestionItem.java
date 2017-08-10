package com.zino.mobilization.weatheryamblz.data.network.response.places;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public class SuggestionItem {

    @SerializedName("description")
    private String description;

    @SerializedName("place_id")
    private String placeId;

    public String getPlaceId() {
        return placeId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "SuggestionItem{" +
                "description='" + description + '\'' +
                ", placeId='" + placeId + '\'' +
                '}';
    }
}
